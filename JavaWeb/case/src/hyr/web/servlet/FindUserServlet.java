package hyr.web.servlet;

import hyr.domain.User;
import hyr.service.UserService;
import hyr.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.
        request.setCharacterEncoding("utf-8");
        //2.
        String id = request.getParameter("id");
        //3.
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        //4.
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
