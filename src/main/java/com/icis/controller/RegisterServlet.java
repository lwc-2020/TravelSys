package com.icis.controller;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.User;
import com.icis.utils.MailUtils;
import com.icis.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDao dao=new UserDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Map<String, String[]> map = request.getParameterMap();
            Set<String> keys = map.keySet();
//            for (String key : keys) {
//                String s = map.get(key)[0];
//                System.out.println(key+"----"+s);
//            }
            User user = new User();
            BeanUtils.populate(user, map);
            //System.out.println(user.getBirthday());

            HttpSession session = request.getSession();

            String checkcode = (String) session.getAttribute("checkcode");
            System.out.println("服务器生产的验证码:" + checkcode);
            System.out.println("输入的验证码:" + (map.get("check")[0]));
            if ((map.get("check")[0]).equalsIgnoreCase(checkcode)) {
                List<User> allUser = dao.getAllUser();
                //System.out.println("????");
                String uuid = UuidUtil.getUuid();
                user.setCode(uuid);
                dao.addUser(user);
                System.out.println(user);
                //发送激活邮件
                MailUtils mailUtils = new MailUtils();
                mailUtils.sendMail(user.getEmail(), "<a href=" + "http://localhost:8085/travel/activation?status=Y" + ">点击激活</a>", "激活邮件");
                session.setAttribute("user", user);


                response.getWriter().write("注册成功");
            } else {
                response.getWriter().write("验证码输入错误");

            }
        } catch (Exception e) {
            //e.printStackTrace();
            response.getWriter().write("用户名已经存在");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
