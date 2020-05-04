package hyr.dao.test;

import hyr.dao.impl.UserDaoImpl;
import hyr.domain.User;
import org.junit.Test;

import java.util.Map;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class UserDaoImplTest {
    private UserDaoImpl ud = new UserDaoImpl();
    @Test
    public void loginTest(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        System.out.println(ud.login(user));
    }

}
