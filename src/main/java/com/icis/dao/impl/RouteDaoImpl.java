package com.icis.dao.impl;

import com.icis.dao.RouteDao;
import com.icis.pojo.Route;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    //根据cid获得路线
    public List<Route> getRouteByCid(int cid) {
        String sql="select* from  tab_route where cid=?";
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid);


        return routes;
    }

    @Override
    public List<Route> getRouteByKeys(String cid, String rname, String currentPageStr, String pageSize) {
        String sql="select*from tab_route where 1=1";
        StringBuilder builder=new StringBuilder(sql);
        //List<Route>routeList=new ArrayList<>();

        Integer currentPage=null;
         if (!cid.equals("")&&!cid.equals("null")){
             builder.append(" and  cid= "+cid);
             if (!currentPageStr.equals("")) {
                 currentPage= Integer.valueOf(currentPageStr);
                 if (currentPage<=0){
                     currentPage=0;
                     builder.append(" limit  "+currentPage);
                     builder.append(" ,5 ");
                 }else {
                     builder.append(" limit "+currentPage*5);
                     builder.append(" ,5 ");
                 }
             }

         }else if (cid.equals("")||cid.equals("null")){
             builder.append(" and rname  like '%"+rname.replace(" ","")+"%'  ");
             if (!currentPageStr.equals("")) {
                 currentPage= Integer.valueOf(currentPageStr);
                 if (currentPage<=0){
                     currentPage=0;
                     builder.append(" limit  "+currentPage);
                     builder.append(" ,5 ");
                 }else {
                     builder.append(" limit "+currentPage*5);
                     builder.append(" ,5 ");
                 }
             }


         }
       List<Route> routes = template.query(builder.toString(), new BeanPropertyRowMapper<>(Route.class));
        System.out.println("sql语句:"+builder);

        return routes ;
    }

    @Override
    public Integer getTotalCount(String cid,String rname) {
        String sql="SELECT COUNT(*)FROM tab_route WHERE 1=1";

        StringBuilder builder=new StringBuilder(sql);
        Integer total=null;
        if (cid.equals("")||cid.equals("null")){
            builder.append(" and  rname  like '%"+rname.replace(" ","")+"%'");
        }else {
            builder.append(" and cid="+cid);

        }
        System.out.println("总记录数拼接语句："+builder);
        total = template.queryForObject(builder.toString(), Integer.class);
        return total;
    }

    @Override
    public List<Route> getPageRoute(Integer currentPage, Integer pagesize,Integer cid) {
        String sql="SELECT*FROM tab_route WHERE cid=? LIMIT ?,? ;";
        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class),cid,currentPage, pagesize);
        return routeList;
    }

}
