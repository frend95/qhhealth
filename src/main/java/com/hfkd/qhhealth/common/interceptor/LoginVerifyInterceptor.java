package com.hfkd.qhhealth.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfkd.qhhealth.common.annotation.Verify;
import com.hfkd.qhhealth.common.util.RedisUtil;
import com.hfkd.qhhealth.common.util.RspEntity;
import com.hfkd.qhhealth.user.model.UserSession;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证token拦截器
 * @author hexq
 * @date 2017/11/28 17:01
 */
@Component
public class LoginVerifyInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private StringRedisTemplate redis;
    @Value("${login.prefix}")
    private String prefix;
    @Value("${login.timeout}")
    private long timeout;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod hm = (HandlerMethod) handler;
        Class<?> clazz = hm.getBeanType();
        Method m = hm.getMethod();

        // 验证method是否加上了Verify注解，没有则放行
        boolean isClassAnnotation = clazz.isAnnotationPresent(Verify.class);
        boolean isMethodAnnotation = m.isAnnotationPresent(Verify.class);
        if (!isMethodAnnotation && !isClassAnnotation) {
            return true;
        }

        // Verify加上hasSession，则验证不通过也会放行
        Verify clazzAnnotation = clazz.getAnnotation(Verify.class);
        Verify methodAnnotation = m.getAnnotation(Verify.class);
        boolean hasSessionClazz = clazzAnnotation != null && clazzAnnotation.hasSession();
        boolean hasSessionMethod = methodAnnotation != null && methodAnnotation.hasSession();
        boolean hasSession = hasSessionClazz || hasSessionMethod;

        String msg = "Session expired, please login again";
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            if (!hasSession) outPrint(response, msg);
            return hasSession;
        }

        // 把uuid token改为单设备登陆: SESSION_id
        //String sessionKey = prefix + token;
        String[] split = token.split("_");
        String sessionKey = prefix + split[0];
        String oriToken = split[1];
        if (!redis.hasKey(sessionKey)) {
            if (!hasSession) outPrint(response, msg);
            return hasSession;
        }

        // 取出redis中的token与userJson
        Map<Object, Object> entries = redis.opsForHash().entries(sessionKey);
        String userJson = (String) entries.get("user");
        String oriTokenDb = (String) entries.get("token");
        if (userJson == null || oriTokenDb == null) {
            if (!hasSession) outPrint(response, msg);
            return hasSession;
        }

        // 验证token，是否重复登陆
        if (!oriToken.equals(oriTokenDb)) {
            if (!hasSession) outPrint(response, msg);
            return hasSession;
        }

        // Verify加上adminOnly只能admin访问
        /*boolean adminOnlyClazz = clazzAnnotation != null && clazzAnnotation.adminOnly();
        boolean adminOnlyMethod = methodAnnotation != null && methodAnnotation.adminOnly();

        if (adminOnlyClazz || adminOnlyMethod) {
            UserSession userSession = JSON.parseObject(userJson, UserSession.class);
            if (!"admin".equals(userSession.getAccount())) {
                outPrint(response, "Access denied");
                return false;
            }
        }*/

        redis.expire(sessionKey, timeout, TimeUnit.SECONDS);
        // 把用户数据保存至请求中
        request.setAttribute("user_info", userJson);
        return true;
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

        boolean isClassAnnotation = clazz.isAnnotationPresent(Verify.class);
        boolean isMethodAnnotation = m.isAnnotationPresent(Verify.class);
        if (!isMethodAnnotation && !isClassAnnotation) {
            return true;
        }

        String msg = "Session expired, please login again";
        RedisConnection redisConn = null;
        try {
            redisConn = RedisUtil.getConnection();
            String token = request.getHeader("Authorization");
            if (StringUtils.isBlank(token)) {
                outPrint(response, msg);
                return false;
            }
            // 把uuid token改为单设备登陆: SESSION_id
            //String sessionKey = prefix + token;
            String[] split = token.split("_");
            String sessionKey = prefix + split[0];
            String oriToken = split[1];
            byte[] sessionKeyBytes = sessionKey.getBytes();
            if (!redisConn.exists(sessionKeyBytes)) {
                outPrint(response, msg);
                return false;
            }
            byte[] sessionByte = redisConn.hGet(sessionKeyBytes, "user".getBytes());
            byte[] oriTokenByte = redisConn.hGet(sessionKeyBytes, "token".getBytes());
            if (ArrayUtils.isEmpty(sessionByte) || ArrayUtils.isEmpty(oriTokenByte)) {
                outPrint(response, msg);
                return false;
            }
            String sessionStr = new String(sessionByte);
            String oriTokenDb = new String(oriTokenByte);
            // 验证token，是否重复登陆
            if (!oriToken.equals(oriTokenDb)) {
                outPrint(response, msg);
                return false;
            }

            Verify clazzAnnotation = clazz.getAnnotation(Verify.class);
            Verify methodAnnotation = m.getAnnotation(Verify.class);
            boolean adminOnlyClazz = clazzAnnotation != null && clazzAnnotation.adminOnly();
            boolean adminOnlyMethod = methodAnnotation != null && methodAnnotation.adminOnly();

            if (adminOnlyClazz || adminOnlyMethod) {
                UserSession userSession = JSON.parseObject(sessionStr, UserSession.class);
                if (!"admin".equals(userSession.getAccount())) {
                    outPrint(response, "Access denied");
                    return false;
                }
            }

            redisConn.expire(sessionKeyBytes, timeout);
            // 把用户数据保存至请求中
            request.setAttribute("user_info", sessionStr);
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
        super.afterCompletion(request, response, handler, ex);
    }

    private void outPrint(HttpServletResponse response, String msg) throws IOException {
        Map<String, Object> returnMap = RspEntity.unauthorized(msg);
        JSONObject returnJson = new JSONObject(returnMap);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(returnJson);
        out.close();
    }

}