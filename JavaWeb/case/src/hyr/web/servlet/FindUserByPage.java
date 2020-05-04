package hyr.web.servlet;

import hyr.domain.PageBean;
import hyr.domain.User;
import hyr.service.UserService;
import hyr.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
@WebServlet("/findUserByPage")
public class FindUserByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String row = request.getParameter("row");
        //第一次访问时
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if(row == null || "".equals(row)){
            row = "5";
        }
        //2.获取查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //3.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pageBean = service.findUserByPage(currentPage,row,condition);

        //4.
        request.setAttribute("pb",pageBean);
        request.setAttribute("condition",condition);

        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
