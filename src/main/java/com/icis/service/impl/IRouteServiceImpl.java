package com.icis.service.impl;

import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.Route;
import com.icis.service.IRouteService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class IRouteServiceImpl implements IRouteService {
    private RouteDao dao=new RouteDaoImpl();
    @Override
    //根据cid获得路线
    public List<Route> getRouteByCid(int cid) {
        List<Route> routeByCid = dao.getRouteByCid(cid);
        return routeByCid;
    }

    @Override
    public List<Route> getRouteByKeys(String cid,String rname,String currentPage,String pageSize) {
        List<Route> routeList = dao.getRouteByKeys(cid, rname, currentPage, pageSize);

        return routeList;
    }

    @Override
    public Integer getTotalCount(String cid,String rname) {

        return dao.getTotalCount(cid,rname);
    }

    @Override
    public List<Route> getPageRoute(Integer currentPage, Integer pagesize, Integer cid) {
        return dao.getPageRoute(cid,currentPage,pagesize);
    }
}
