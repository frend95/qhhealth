package com.hfkd.qhhealth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库连接池c3p0的配置
 * @author hexq
 * @date 2017/11/15 14:02
 */
@Configuration
public class DataSourceConfig {

    /**
     * 初始化c3p0连接池
     */
    @Bean(name = "dataSource")
    //限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者
    @Qualifier(value = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }
}