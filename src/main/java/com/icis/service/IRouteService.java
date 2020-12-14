package com.icis.service;

import com.icis.pojo.Route;

import java.util.List;
import java.util.Map;

public interface IRouteService {
    //根据cid获得路线
    public List<Route> getRouteByCid(int cid);
    //根据条件获得当前页路线
    public List<Route>getRouteByKeys(String cid,String rname,String currentPage,String pageSize);
    //获得总记录数
    public Integer getTotalCount(String cid,String rname);
    //获得分页记录
    public List<Route> getPageRoute(Integer currentPage,Integer pagesize,Integer cid);
}
