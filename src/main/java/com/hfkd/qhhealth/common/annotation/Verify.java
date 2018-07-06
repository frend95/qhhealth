package com.hfkd.qhhealth.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 用于登录token验证的自定义注解
 * @author hexq
 * @date 2017/11/28 17:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Documented
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Verify {
    @AliasFor("adminOnly")
    boolean value() default false;

    @AliasFor("value")
    boolean adminOnly() default false;
}
