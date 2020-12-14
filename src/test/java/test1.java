import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import org.junit.Test;

import java.util.List;

public class test1 {
    private static UserDao dao=new UserDaoImpl();
    @Test
    public void getAll(){
        List<User> allUser = dao.getAllUser();
        System.out.println(allUser);
    }
    @Test
    public void add(){
        String sql="INSERT INTO tab_user VALUES(NULL,'张三','222','赵四','1980-02-23','男','18776126983','1657770704@qq.com','Y','23123123');";
        User user=new User(1,"周四","333","zhousi","1999-03-08", "男","18777126312","1657770704@qq.com", "Y","1232132");
    }
    @Test
    public void getOne(){
       User user=new User();
       user.setUsername("8866");
        System.out.println(user);


        User user1 = dao.findUserByUserName("668866");
        System.out.println(user1);
    }
    @Test
    public void getAllCateGory(){
        List<Category> allCategory = dao.getAllCategory();
        for (Category category : allCategory) {

            System.out.println(category);
        }
    }

}
