package com.base.springboot.common.config;

import com.base.springboot.common.interceptor.FullSqlInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangCheng
 * @title: MybatisConfig
 * @projectName springboot
 * @description: mybatis配置类
 * @date 2019/9/30 10:17
 */

@Configuration
public class MybatisConfig {

    //注入数据源JWPDDataSource
    @Autowired
    @Qualifier("JWPDDataSource")
    public DataSource JWPDDataSource;

    //注入数据源LKJDataSource
    @Autowired
    @Qualifier("LKJDataSource")
    public DataSource LKJDataSource;


    //声明动态数据源,默认值为JWPDDataSource
    @Bean("dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(){
        //动态数据源集合
        Map<Object, Object> targetDataSourcesMap = new HashMap<>(2);
        targetDataSourcesMap.put(DataSourceEnum.jwpd.name(),JWPDDataSource);
        targetDataSourcesMap.put(DataSourceEnum.lkj.name(),LKJDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource(targetDataSourcesMap,JWPDDataSource);

        return dynamicDataSource;
    }


    @Bean(name="pageHelper")
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect", "mysql");
        pageHelper.setProperties(p);

        return pageHelper;
    }


    //sql打印插件
    @Bean(name="fullSqlInterceptor")
    public FullSqlInterceptor fullSqlInterceptor(){
        return new FullSqlInterceptor();
    }

    /**
     * 声明sql会话
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("pageHelper")PageHelper pageHelper,@Qualifier("fullSqlInterceptor")FullSqlInterceptor fullSqlInterceptor) throws Exception{
        //声明sql会话工厂
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dynamicDataSource());
        //设置扫描mybatisXml的路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/dao/*/*.xml"));
        factoryBean.setPlugins(new Interceptor[] {pageHelper(), fullSqlInterceptor});//添加分页插件
        //返回sql会话
        return factoryBean.getObject();
    }


    /**
     * 声明事务管理器
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    /**
     * 声明sqlSession模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new  SqlSessionTemplate(sqlSessionFactory);
    }




}
