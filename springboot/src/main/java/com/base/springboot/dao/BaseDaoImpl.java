package com.base.springboot.dao;

import com.base.springboot.common.macb.query.ListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangCheng
 * @title: BaseDaoImpl
 * @projectName springboot
 * @description: DAO层接口实现类
 * @date 2019/9/30 10:38
 */
@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;


    @Override
    public List<Object> getObjectList(String statement, Map<String, Object> paramMap) {

        List<Object> list=null;
        try {
            list=sqlSessionTemplate.selectList(statement,paramMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public <T> ListVo<T> getObjectPage(String start,String limit,String statement, Map<String, Object> paramMap) {
        try {
//            RowBounds rowBounds=new RowBounds(Integer.parseInt(start),Integer.parseInt(limit));
//            List<T> list=sqlSessionTemplate.selectList(statement,paramMap,rowBounds);
            ListVo<T> listVo = new ListVo<T>();
            PageHelper.startPage(Integer.parseInt(start),Integer.parseInt(limit),true);
            List<T> list=sqlSessionTemplate.selectList(statement,paramMap);
            listVo.setList(list);
            Page<T> page = (Page<T>)list;
            listVo.setTotalSize((int)page.getTotal());
            return listVo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertObject(String statement, Object object) {
        try{
            sqlSessionTemplate.insert(statement,object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
