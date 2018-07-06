package com.hfkd.qhhealth.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 在方法上加上此注解并配置相应接口的缓存名以刷新Redis中的缓存
 * @author hexq
 * @date 2017/11/16 14:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Documented
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface CacheRefresh {
    //需要刷新的缓存名称
    String value() default "";
}
