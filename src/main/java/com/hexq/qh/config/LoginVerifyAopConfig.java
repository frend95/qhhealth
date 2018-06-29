package com.hexq.qh.config;

import com.hexq.qh.common.annotation.Verify;
import com.hexq.qh.common.util.RedisUtil;
import com.hexq.qh.common.util.RspUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 登陆session验证AOP
 * @author hexq
 * @date 2018/6/1 15:03
 */
//@Aspect
//@Configuration
public class LoginVerifyAopConfig {
    @Value("${login.prefix}")
    String prefix;
    @Value("${login.timeout}")
    long timeout;

    @Pointcut("@annotation(com.hexq.qh.common.annotation.Verify)")
    public void verify() {}

    @Around("verify()")
    public Object verifySession(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = RspUtil.unauthorized("session expired, please login again");
        RedisConnection redisConn = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Class<?> clazz = joinPoint.getTarget().getClass();
            boolean isClassAnnotation = method.isAnnotationPresent(Verify.class);
            boolean isMethodAnnotation = clazz.isAnnotationPresent(Verify.class);
            if (!isMethodAnnotation && !isClassAnnotation) {
                return true;
            }

            // 获取参数里的token
            Object[] args = joinPoint.getArgs();
            String token = getTokenInArgs(args, method);
            if (StringUtils.isBlank(token)) {
                return proceed;
            }
            // 根据token获取redis中的用户信息
            redisConn = RedisUtil.getConnection();
            String sessionKey = prefix + token;
            byte[] sessionKeyBytes = sessionKey.getBytes();
            byte[] loginInfo = redisConn.get(sessionKeyBytes);
            // 是否登录校验
            if (loginInfo == null) {
                return proceed;
            }
            redisConn.expire(sessionKeyBytes, timeout);
            // 将token放入redis以在请求完成之前可以随时取出用户信息
            String tokenKey = "TOKEN_" + Thread.currentThread().getId();
            System.out.println("interceptor: " + Thread.currentThread().getId() + "_" + token);
            redisConn.setEx(tokenKey.getBytes(), 30, token.getBytes());
            // 方法的返回值
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (redisConn != null) {
                try {
                    redisConn.close();
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return proceed;
    }

    private String getTokenInArgs(Object[] args, Method method) {
        String token = null;
        if (ArrayUtils.isEmpty(args)) {
            return null;
        }
        if (args.length == 1 && args[0] instanceof HashMap) {
                token = (String) ((HashMap) args[0]).get("token");
        }
        Annotation[][] annotations = method.getParameterAnnotations();
        // todo 如果用aop来验证参数，则controoler的method必须要有参数，这样限制比较大

        return token;
    }


}
