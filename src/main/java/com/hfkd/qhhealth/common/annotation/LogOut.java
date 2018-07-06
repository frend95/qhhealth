package com.hfkd.qhhealth.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 日志输出注解
 * @author hexq
 * @date 2017/11/16 14:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Documented
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface LogOut {
    String value() default "";
}
