package com.hexq.qh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * MybatisPlus配置
 * @author hexq
 * @date 2017/11/15  14:02
 */
@Configuration
@MapperScan("com.hexq.qh.*.mapper*")
public class MybatisPlusConfig {

    /**
     * mybatis-plus 分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }
}
