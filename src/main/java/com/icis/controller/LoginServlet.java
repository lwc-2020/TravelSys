package com.icis.controller;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao dao=new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           HttpSession session = request.getSession();
           String checkcode = (String) session.getAttribute("checkcode");
          // System.out.println("登录页面生成的验证码" + checkcode);
           String auto_login = request.getParameter("auto_login");

//           Map<String, String[]> map = request.getParameterMap();
//           Set<String> keys = map.keySet();
//        for (String key : keys) {
//            System.out.println(key+"---"+map.get(key)[0]);
//        }
          String password=request.getParameter("password");
           String check = request.getParameter("check");
           String account = request.getParameter("username");
           if (check.equalsIgnoreCase(checkcode)) {
               //检查用户名是否存在 不存在的话在异常处写入提示信息
               User userByUserName = dao.findUserByUserName(account);
               //判断密码是否正确
               if (password.equals(userByUserName.getPassword())) {
                   //判断是否激活
                   if (userByUserName.getStatus().equals("Y")){
                       session.setAttribute("loginUser",userByUserName);

                       response.getWriter().write("登录成功");
                       //判断是否选择记住密码
                       System.out.println("是否记住账号密码:"+auto_login);
                   }else {
                       response.getWriter().write("账户尚未激活");
                   }

               } else {
                   response.getWriter().write("密码错误");
               }
               //response.getWriter().write("验证码正确");
           } else {
               response.getWriter().write("验证码错误请重新输入");
           }
       }catch (Exception e){
           response.getWriter().write("账号不存在");
       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
