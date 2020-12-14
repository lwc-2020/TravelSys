package com.icis.controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/demo")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acount = request.getParameter("acount");
        String password = request.getParameter("password");
        System.out.println(acount+"------"+password);
        Cookie cookie=new Cookie("demoUser", URLEncoder.encode(acount+"="+password,"utf-8"));
        cookie.setPath("/");
        cookie.setMaxAge(69*60);
        response.addCookie(cookie);
        System.out.println("设置cookie");
        request.getRequestDispatcher("getCookie");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
