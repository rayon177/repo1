package hyr.service;

import hyr.domain.PageBean;
import hyr.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public interface UserService {
    /**
     * @Description     查询所有用户信息
     * @Author hyr
     * @Date 2019-09-26 10:51
     */
    public List<User> findAll();

    public  User login(User user);

    void addUser(User user);

    void update(User user);

    User findUserById(String id);

    void deleteUser(String id);

    void deleteSelectedUser(String[] ids);

    PageBean<User> findUserByPage(String currentPage, String row, Map<String, String[]> condition);
}
