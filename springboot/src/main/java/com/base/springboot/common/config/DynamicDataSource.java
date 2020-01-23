package com.base.springboot.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zhangCheng
 * @title: DynamicDataSource
 * @projectName springboot
 * @description: 动态数据源
 * @date 2019/9/30 10:03
 */
public class DynamicDataSource extends AbstractRoutingDataSource {


    /**
     * 有参构造方法,声明对象的时候执行,调用父类AbstractRoutingDataSource的方法
     * @param targetDataSources  数据源Map集合
     * @param defaultTargetDataSource  默认数据源
     */
    public DynamicDataSource(Map<Object,Object> targetDataSources, DataSource defaultTargetDataSource) {
        //将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.addDataSourceKeys(targetDataSources.keySet());
        //设置数据源集合
        super.setTargetDataSources(targetDataSources);
        //设置默认数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }

    /**
     * 重写determineCurrentLookupKey方法,这个方法返回一个key值,
     * 通过这个key值执行determineTargetDataSource方法,获取当前的数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }


}
