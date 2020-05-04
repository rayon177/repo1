package hyr.service.impl;

import hyr.dao.UserDao;
import hyr.dao.impl.UserDaoImpl;
import hyr.domain.PageBean;
import hyr.domain.User;
import hyr.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao来完成查询
        return dao.findall();
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public User findUserById(String id) {

        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public void deleteSelectedUser(String[] ids) {
        for (String id : ids) {
            dao.delete(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _row, Map<String, String[]> condition) {
        Integer currentPage = Integer.parseInt(_currentPage.trim());
        Integer row = Integer.parseInt(_row.trim());
        currentPage = currentPage < 1 ? 1 : currentPage;
        //创建PageBean对象
        PageBean<User> pageBean = new PageBean<>();
        //装载对象
        pageBean.setCurrentPage(currentPage);
        pageBean.setRow(row);
        //调用dao查询总记录数
        int totalcount = dao.finTotalCount(condition);
        pageBean.setTotalCount(totalcount);
        pageBean.setTotalPage(totalcount % row == 0 ? totalcount / row : (totalcount / row + 1));
        //调用dao查询符合条件的记录 一页的list
        int start = (currentPage - 1) * row;
        List<User> list =  dao.findByPage(start,row,condition);
        pageBean.setList(list);

        return pageBean;

    }


}
