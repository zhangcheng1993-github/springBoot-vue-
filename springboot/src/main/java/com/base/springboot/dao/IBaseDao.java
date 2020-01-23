package com.base.springboot.dao;

import com.base.springboot.common.macb.query.ListVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author zhangCheng
 * @title: IBaseDao
 * @projectName springboot
 * @description: DAO接扣
 * @date 2019/9/30 10:37
 */
public interface IBaseDao {

    public List<Object> getObjectList(String statement, Map<String,Object> paramMap);


    public <T> ListVo<T> getObjectPage(String start,String limit,String statement, Map<String,Object> paramMap);

    public void insertObject(String statement, Object object);
}
