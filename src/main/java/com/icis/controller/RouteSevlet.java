package com.icis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.service.IRouteService;
import com.icis.service.impl.IRouteServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 接收Route模块的所有的请求  init() service() destroy()
@WebServlet("/route/*")
public class RouteSevlet extends  BaseServlet{
    // 创建一个service层的类对象
    private IRouteService routeService=new IRouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    //根据cid获得旅游线路
    public void getRouteByCid(HttpServletRequest request, HttpServletResponse response){
       try {

           //调用查询 cid
           String cid = request.getParameter("cid");
           System.out.println("cid:"+cid);
           String rname = URLDecoder.decode(request.getParameter("rname"), "utf-8");
           String currentPage = request.getParameter("currentPage");
           System.out.println("rname:"+rname);
           Map<String, String[]> parameterMap = request.getParameterMap();
           Set<String> keys = parameterMap.keySet();
           for (String key : keys) {
               System.out.println(key+"------------"+parameterMap.get(key)[0]);
           }



               //调用service查询数据
              // List<Route> routes = routeService.getRouteByCid(cid1);
               List<Route> routes = routeService.getRouteByKeys(cid, rname, currentPage, "5");


               //把集合转化为字符串  传递到前端页面
               String routesStr = mapper.writeValueAsString(routes);
               //把字符数据相应该前端
               response.getWriter().write(routesStr);



       }catch (Exception e){
           e.printStackTrace();
       }
    }
    //获得总记录数方法
    public void getCurrentPageBean(HttpServletRequest request,HttpServletResponse response){
        try {
            String cidStr = request.getParameter("cid");
            String rname = URLDecoder.decode(request.getParameter("rname"), "utf-8");
            System.out.println("这里是总记录数从前端得到的rname："+rname);
                //获得总记录数
                Integer totalCount = routeService.getTotalCount(cidStr,rname);
                System.out.println("总记录数:" + totalCount);
                response.getWriter().write(totalCount.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //添加线路方法
    public void addRoute(HttpServletRequest request, HttpServletResponse response){
        System.out.println("添加线路方法");
    }
    //修改线路方法
    public void updateRoute(){
        System.out.println("修改线路的方法");
    }
}
