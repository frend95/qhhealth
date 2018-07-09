package com.hfkd.qhhealth.config;

import com.hfkd.qhhealth.common.util.RedisUtil;
import com.hfkd.qhhealth.common.util.RspUtil;
import com.hfkd.qhhealth.common.interceptor.ReturnVerifyInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 缓存AOP配置类
 * @author hexq
 * @date 2017/11/15 14:02
 */
@Aspect
@Configuration
public class CacheAopConfig {
    /**
     * 更新缓存切入点
     */
    @Pointcut("@annotation(com.hfkd.qhhealth.common.annotation.CacheRefresh)")
    public void cacheRefresh() {}

    /**
     * 缓存切入点
     */
    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void cacheable() {}

    /**
     * 更新缓存通知
     * @param joinPoint 连接点
     * @return Target的返回值weaving后的产生的对象
     * @throws Throwable
     */
    @Around("cacheRefresh()")
    public Object refreshCache(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            /*MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取注解
            CacheRefresh annotation = signature.getMethod().getAnnotation(CacheRefresh.class);
            //缓存名称
            String cacheName = annotation.value();*/
            //类名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            if (StringUtils.isNotBlank(className)) {
                //删除以当前类名为前缀的缓存
                RedisUtil.bathDelByPref("CACHE_" + className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinPoint.proceed();
    }

    /**
     * 由于Cacheable注解也会将错误的值缓存，所以将缓存名放入Redis，便于在拦截器{@link ReturnVerifyInterceptor}里删除错误的缓存
     * @param joinPoint 连接点
     * @return Target的返回值weaving后的产生的对象
     * @throws Throwable
     */
    @Around("cacheable()")
    public Object saveCacheName(ProceedingJoinPoint joinPoint) throws Throwable {
        Map proceed = (Map) joinPoint.proceed();
        try {
            //方法的返回值
            int code = (int) proceed.get("code");
            //如果返回码为成功，则不处理
            if (RspUtil.CODE_OK == (code)) {
                return proceed;
            }
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取注解
            Cacheable annotation = signature.getMethod().getAnnotation(Cacheable.class);
            //缓存名称
            String[] cacheNames = annotation.value();
            if (ArrayUtils.isNotEmpty(cacheNames) && StringUtils.isNotBlank(cacheNames[0])) {
                String key = "IncorrectResult_" + Thread.currentThread().getId();
                //以线程id为key将缓存名存入redis
                RedisUtil.set(key, cacheNames[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proceed;
    }

}
