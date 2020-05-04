package hyr.dao;

import hyr.domain.User;
import hyr.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description        操作数据库中表的类
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());

    /**
     * @Description  登陆方法，loginUser有用户名和密码。返回包含用户全部数据，没有查到则返回null
     * @Author hyr
     * @Date 2019-09-26 10:51
     */
    public User login(User loginUser){
        User user = null;
        try {
            user = template.queryForObject("select * from user where username = ? and password =?",
                    new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
        } catch (DataAccessException e) {

            e.printStackTrace(); //记录日志
            return user;
        }
        return user;
    }

}
