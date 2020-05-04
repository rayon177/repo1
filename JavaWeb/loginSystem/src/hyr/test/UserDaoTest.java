package hyr.test;

import hyr.dao.UserDao;
import hyr.domain.User;
import org.junit.Test;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class UserDaoTest {

   @Test
   public void testLogin(){
      User login =new User();
      login.setUsername("zhangsan");
      login.setPassword("123123");

      UserDao dao = new UserDao();
      User user = dao.login(login);
      System.out.println(user);
   }

}
