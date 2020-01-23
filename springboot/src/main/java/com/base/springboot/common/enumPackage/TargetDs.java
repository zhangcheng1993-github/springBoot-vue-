package com.base.springboot.common.enumPackage;

import java.lang.annotation.*;

/**
 * @author zhangCheng
 * @title: TargetDs
 * @projectName springboot
 * @description: 当前数据源注解
 * @date 2019/9/30 11:00
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDs {

    /**
     * 数据源key值
     * @return
     */
    String value();
}
