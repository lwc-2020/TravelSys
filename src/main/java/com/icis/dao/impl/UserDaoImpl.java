package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private  JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> getAllUser() {
        String sql="select *from tab_user";
        List<User> userList = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public void addUser(User user) {
       String sql="INSERT INTO tab_user(uid,username,password,name,birthday,sex,telephone,email,code) VALUES(NULL,?,?,?,?,?,?,?,?);";
       template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getCode());
    }

    @Override
    public void activation(User user) {
    String sql="update tab_user set status=? where code=?";
    template.update(sql,user.getStatus(),user.getCode());
        System.out.println("激活成功");
    }

    @Override
    public User findUserByUserName(String account) {
        String sql="select*from tab_user where username=?";
        User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),account);

       return user1;
    }

    @Override
    public List<Category> getAllCategory() {
        String sql="SELECT*FROM tab_category ORDER BY cid;";
        List<Category> categoryList = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return categoryList;
    }
}
