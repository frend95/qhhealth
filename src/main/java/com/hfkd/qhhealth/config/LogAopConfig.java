package com.hfkd.qhhealth.config;

import com.hfkd.qhhealth.common.annotation.LogOut;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 打印Controller日志AOP
 * @author hexq
 * @date 2018/6/1 15:03
 */
@Aspect
@Configuration
public class LogAopConfig {
    private final Log log = LogFactory.getLog(getClass());

    @Pointcut("@annotation(com.hfkd.qhhealth.common.annotation.LogOut)")
    public void logOut() {}

    @Around("logOut()")
    public Object printLogBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取注解
        LogOut annotation = signature.getMethod().getAnnotation(LogOut.class);
        //注解的值
        String name = annotation.value();
        log.info(name + "  Starting...");
        log.debug("request args: " + Arrays.toString(joinPoint.getArgs()));
        //方法的返回值
        Object proceed = joinPoint.proceed();
        if (proceed.getClass() == HashMap.class) {
            log.debug("return value: " + proceed);
        }
        log.info(name + "  Ended...");
        return proceed;
    }



    /*@Before("logOut()")
    public void printLogBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取注解
        LogOut annotation = signature.getMethod().getAnnotation(LogOut.class);
        //注解的值
        String name = annotation.value();
        log.info(name + "  Starting...");
        log.debug("request args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "logOut()", returning = "ret")
    public void printLogAfter(Object ret) {
        if (ret.getClass() == HashMap.class) {
            log.debug(ret);
        }

    }*/


}
