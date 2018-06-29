package com.hexq.qh.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hexq.qh.common.annotation.Verify;
import com.hexq.qh.common.util.HttpHelper;
import com.hexq.qh.common.util.RedisUtil;
import com.hexq.qh.common.util.RspUtil;
import com.hexq.qh.common.util.SerializeUtil;
import com.hexq.qh.user.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 验证token拦截器
 * @author hexq
 * @date 2017/11/28  17:01
 */
@Component
public class LoginVerifyInterceptor extends HandlerInterceptorAdapter {
    @Value("${login.prefix}")
    String prefix;
    @Value("${login.timeout}")
    long timeout;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return verify(request, response, handler);
    }

    /**
     * 用户登录验证
     */
    private boolean verify(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod hm = (HandlerMethod) handler;
        Class<?> clazz = hm.getBeanType();
        Method m = hm.getMethod();
        if (clazz == null || m == null) {
            return true;
        }

        boolean isClassAnnotation = clazz.isAnnotationPresent(Verify.class);
        boolean isMethodAnnotation = m.isAnnotationPresent(Verify.class);
        if (!isMethodAnnotation && !isClassAnnotation) {
            return true;
        }

        String msg = "Session expired, please login again";
        RedisConnection redisConn = null;
        try {
            redisConn = RedisUtil.getConnection();
            String token = HttpHelper.getTokenByRequest(request);
            String sessionKey = prefix + token;
            byte[] sessionKeyBytes = sessionKey.getBytes();
            byte[] loginInfo = redisConn.get(sessionKeyBytes);
            // 是否登录校验
            if (ArrayUtils.isEmpty(loginInfo)) {
                outPrint(response, msg);
                return false;
            }

            Verify clazzAnnotation = clazz.getAnnotation(Verify.class);
            Verify methodAnnotation = m.getAnnotation(Verify.class);
            boolean adminOnlyClazz = clazzAnnotation != null && clazzAnnotation.adminOnly();
            boolean adminOnlyMethod = methodAnnotation != null && methodAnnotation.adminOnly();

            if (adminOnlyClazz || adminOnlyMethod) {
                User user = (User) SerializeUtil.deserialization(loginInfo);
                if (!"admin".equals(user.getAccount())) {
                    outPrint(response, "Access denied");
                    return false;
                }
            }

            redisConn.expire(sessionKeyBytes, timeout);
            // 将token放入redis以在请求完成之前可以随时取出用户信息
            String tokenKey = "TOKEN_" + Thread.currentThread().getId();
            redisConn.setEx(tokenKey.getBytes(), 30, token.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            outPrint(response, msg);
            return false;
        } finally {
            if (redisConn != null) {
                try {
                    redisConn.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 删除本次请求临时放入的token TODO 考虑到性能损失，暂时去除，改为设置ttl
//        RedisUtil.del("TOKEN_" + Thread.currentThread().getId());
        super.afterCompletion(request, response, handler, ex);
    }

    private void outPrint(HttpServletResponse response, String msg) throws IOException {
        Map<String, Object> returnMap = RspUtil.unauthorized(msg);
        JSONObject returnJson = new JSONObject(returnMap);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(returnJson);
        out.close();
    }

}