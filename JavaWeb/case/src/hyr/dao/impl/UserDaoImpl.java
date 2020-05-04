package hyr.dao.impl;

import hyr.dao.UserDao;
import hyr.domain.PageBean;
import hyr.domain.User;
import hyr.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findall() {
        String sql = "select * from user ";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User login(User user) {
        String sql = "select * from user where username = ? and password = ?";
        try {
            User nuser = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    user.getUsername(), user.getPassword());
            return nuser;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void update(User user) {
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        System.out.println(user);
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public User findById(int id) {

        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    @Override
    public int finTotalCount(Map<String, String[]> condition) {
        //构造sql语句
        String sql = " select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历condition Map
        Set<String> set = condition.keySet();
        //定义参数集合
        ArrayList<Object> params = new ArrayList<>();
        for (String key : set) {
            //排除分页条件参数
            if ("currentPage".equals(key)||"row".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        //System.out.println(params);

       return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, Integer row, Map<String, String[]> condition) {
        //构造sql语句
        String sql = " select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历condition Map
        Set<String> set = condition.keySet();
        //定义参数集合
        ArrayList<Object> params = new ArrayList<>();
        for (String key : set) {
            //排除分页条件参数
            if ("currentPage".equals(key)||"row".equals(key)){
                continue;
            }

            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append("limit ?,?");
        params.add(start);
        params.add(row);
       // System.out.println(sb.toString());
       // System.out.println(params);
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
