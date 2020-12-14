package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
    //获得所有用户
    public List<User> getAllUser();

    public void addUser(User user);
    public void activation(User user);
    //根据用户名查询一个用户信息
    public User findUserByUserName(String account);
    //获得所有排序好的旅游路线
    public List<Category> getAllCategory();
}
