package hyr.dao;

import hyr.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Description   用户操作DAO
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public interface UserDao {
    public List<User> findall();

    User login(User user);

    void addUser(User user);

    void update(User user);

    User findById(int parseInt);

    void delete(int id);

    int finTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, Integer row, Map<String, String[]> condition);
}
