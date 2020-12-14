package com.icis.controller;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.User;
import com.icis.utils.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/activation")
public class ActivationServlet extends HttpServlet {
    private static UserDao dao=new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String status = request.getParameter("status");
//        Map<String, String[]> map = request.getParameterMap();
//        Set<String> keys = map.keySet();
//        for (String key : keys) {
//            System.out.println(map.get(key)[0]);
//        }
           System.out.println(status);
           if (status!=null) {
               user.setStatus(status);
               System.out.println(user);


               System.out.println(status);
               dao.activation(user);
               request.getSession().setAttribute("activation",user);
               request.getSession().setAttribute("loginUser",user);
               response.getWriter().write("激活成功:"+status.toString());
           }





        //response.sendRedirect("register_ok.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
