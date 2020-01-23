package com.base.springboot.common.config;

import com.base.springboot.common.enumPackage.TargetDs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhangCheng
 * @title: DynamicDataSourceAspect
 * @projectName springboot
 * @description: 动态数据源AOP
 * @date 2019/9/30 10:55
 */
@Aspect
@Order(-1)  // 该切面应当先于 @Transactional 执行
@Component
public class DynamicDataSourceAspect {


    /**
     * 前置通知,进入切点之前,先切换数据源
     * @param point
     * @param targetDs
     */
    @Before("@annotation(targetDs)")
    public void switchDataSource(JoinPoint point, TargetDs targetDs) {
        //判断,如果没有此数据源
        if (!DynamicDataSourceContextHolder.containDataSourceKey(targetDs.value())){
            System.out.println("没有找到key为[{}]的数据源,所以当前还是使用默认数据源!"+targetDs.value());
        }else {
            DynamicDataSourceContextHolder.setDataSourceKey(targetDs.value());
            System.out.println("方法"+point.getSignature().getName()+"上发现@TargetDs注解,"+"当前数据源已经切换为[{}]!"+targetDs.value());
        }
    }


    /**
     * 后置通知,切合方法执行完成之后,重置数据源
     * @param point
     * @param targetDs
     */
    @After("@annotation(targetDs)")
    public void restoreDataSource(JoinPoint point, TargetDs targetDs) {
        System.out.println("重置数据源 [" + DynamicDataSourceContextHolder.getDataSourceKey()
                + "] in Method [" + point.getSignature() + "]");
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }






}
