package com.base.springboot.common.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhangCheng
 * @title: DynamicDataSourceContextHolder
 * @projectName springboot
 * @description: 动态数据源上下文操作类
 * @date 2019/9/30 9:43
 */
public class DynamicDataSourceContextHolder {

    /**
     * 静态ThreadLocal常量contextHolder,用来装当前线程的数据源key
     */
    public static final ThreadLocal<String> contextHolder=new ThreadLocal<>();



    /**
     * 数据源的 key集合，用于切换时判断数据源是否存在
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();


    /**
     * 获取contextHolder值(数据源key)的方法(获取当前数据源)
     */
    public static String getDataSourceKey(){
        return contextHolder.get();
    }


    /**
     * 写入contextHolder值(数据源key)的方法(写入当前数据源)
     */
    public static void setDataSourceKey(String key){
        contextHolder.set(key);
    }


    /**
     * 清除contextHolder值(数据源key)的方法(写入当前数据源)
     */
    public static void clearDataSourceKey(){
        contextHolder.remove();
    }


    /**
     * 判断是否包含数据源
     * @param key 数据源key
     * @return boolean
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源keys
     * @param keys
     * @return boolean
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
        return dataSourceKeys.addAll(keys);
    }


}
