package com.hfkd.qhhealth.config;

import com.hfkd.qhhealth.common.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

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
     * 更新缓存通知
     */
    @Around("cacheRefresh()")
    public Object refreshCache(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            RedisUtil.bathDelByPref("API_CACHE::");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joinPoint.proceed();
    }

}
