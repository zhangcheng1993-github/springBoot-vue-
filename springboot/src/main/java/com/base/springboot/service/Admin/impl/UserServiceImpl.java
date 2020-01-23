package com.base.springboot.service.Admin.impl;

import com.base.springboot.common.macb.query.ListVo;
import com.base.springboot.dao.IBaseDao;
import com.base.springboot.entity.User;
import com.base.springboot.service.Admin.IUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    public IBaseDao baseDao;



    @Override
    public List<Object> getObjectList(Map<String, Object> paramMap) {
        return baseDao.getObjectList("admin.getUserList",paramMap);
    }


    @Override
    public ListVo<Object> getObjectPage(String start, String limit, Map<String, Object> paramMap) {
        return baseDao.getObjectPage(start,limit,"admin.getUserList",paramMap);
    }

    @Override
    public void insertUser(User user) {
        baseDao.insertObject("lkjadmin.insertUser",user);

        String s="";
        String[] arr=s.split(",");
        String W=arr[1];
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("id",2);
        System.out.println(baseDao.getObjectList("lkjadmin.getUserList",paramMap));
        throw new RuntimeException("");
    }
}
