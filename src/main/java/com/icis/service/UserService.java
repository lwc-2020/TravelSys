package com.icis.service;

import com.icis.pojo.User;

import java.util.List;

public interface UserService {
    //获得所有用户
    public List<User> getAllUser();
    //注册用户  添加用户
    public void addUser(User user);
}
