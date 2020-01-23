package com.base.springboot.common.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceProperties {


    @ConfigurationProperties(prefix = "spring.dynamic-datasource.druid-datasources.jwpd")
    @Bean(name = "JWPDDataSource")
    public DataSource JWPDDataSource(StandardEnvironment env){
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return common(env,druidDataSource);
    }



    @ConfigurationProperties(prefix = "spring.dynamic-datasource.druid-datasources.lkj")
    @Bean(name = "LKJDataSource")
    public DataSource LKJDataSource(StandardEnvironment env){
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return common(env,druidDataSource);
    }

    public DataSource common(StandardEnvironment env, DruidDataSource druidDataSource){
        Properties properties = new Properties();
        PropertySource<?> appProperties =  env.getPropertySources().get("applicationConfig: [classpath:/application.yml]");
        Map<String,Object> source = (Map<String, Object>) appProperties.getSource();
        properties.putAll(source);
        druidDataSource.configFromPropety(properties);
        return druidDataSource;
    }
}
