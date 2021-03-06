package hyr.jdbc;

import hyr.util.JDBCUtils;

import javax.security.auth.login.LoginContext;
import java.sql.*;
import java.util.Scanner;

/**
 * @Description * 练习：
 * * 		* 需求：
 * * 			1. 通过键盘录入用户名和密码
 * * 			2. 判断用户是否登录成功
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JDBCDemo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();

        if(new JDBCDemo2().login2(username,password)){
            System.out.println("登陆成功");
        }
        else {
            System.out.println("账号或密码错误");
        }

    }

    /**
     * 登录方法  //sql注入
     */
    public boolean login2(String username ,String password){
        if(username == null || password == null){
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        Statement stmt =  null;
        ResultSet rs = null;
        //1.获取连接
        try {
            conn =  JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '"+username+"' and password = '"+password+"' ";
            System.out.println(sql);
            //3.获取执行sql的对象
            stmt = conn.createStatement();
            //4.执行查询
            rs = stmt.executeQuery(sql);
            //5.判断
           /* if(rs.next()){//如果有下一行，则返回true
                return true;
            }else{
                return false;
            }*/
            return rs.next();//如果有下一行，则返回true

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }


        return false;
    }

    /**
     * 登录方法,使用PreparedStatement实现
     */

    public boolean login(String username, String password)  {
        if (username == null || password == null){
            return false;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,pstmt,conn);
        }

        return false;
    }

}
