package com.base.springboot.service.Admin;

import com.base.springboot.common.macb.query.ListVo;
import com.base.springboot.entity.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public List<Object> getObjectList(Map<String,Object> paramMap);

    public ListVo<Object> getObjectPage(String start, String limit, Map<String,Object> paramMap);

    public void insertUser(User user);
}
