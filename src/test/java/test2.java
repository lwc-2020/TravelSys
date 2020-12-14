import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.Route;
import com.icis.service.IRouteService;
import com.icis.service.impl.IRouteServiceImpl;
import org.junit.Test;

import java.util.List;

public class test2 {
    private RouteDao dao=new RouteDaoImpl();
    @Test
    public void getRouteByCid(){
        List<Route> routeByCid = dao.getRouteByCid(1);
        for (Route route : routeByCid) {
            System.out.println(route.getRname());
        }


    }

    @Test
    public void getRouteList(){
//        List<Route> pageRoute = dao.getPageRoute(1, 3, 1);
//        for (Route route : pageRoute) {
//            System.out.println(route);
//        }
        System.out.println("----------------");
        List<Route> routeList = dao.getRouteByKeys("", "广西", "0", "5");


        System.out.println("------------------");
        IRouteService service=new IRouteServiceImpl();
        List<Route> routeByKeys = service.getRouteByKeys("", "广西", "0", "5");
        System.out.println(routeList);
        System.out.println("----------------------------");
        System.out.println(routeByKeys);
    }
    @Test
    public void getCount(){
        Integer totalCount = dao.getTotalCount("", "广西");
        System.out.println(totalCount);
    }

}
