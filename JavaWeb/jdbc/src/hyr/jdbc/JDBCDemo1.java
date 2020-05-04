package hyr.jdbc;

import hyr.domain.Emp;
import hyr.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JDBCDemo1 {
    public static void main(String[] args) {
        var jdbc = new JDBCDemo1();
        var emps = jdbc.findAll2();
        System.out.println(emps.size());
    }

    /**
     * @Description 查询所有emp对象
     * @Author hyr
     * @Date 2019-09-26 10:51
     */

    public List<Emp> findAll()  {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        ArrayList<Emp> emps = null;
        try {
            // 0. 导入jar包
//        1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取连接
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            //3.定义sql
            String sql = "select * from emp";
            // 4. 获取执行sql的对象
            stmt = conn.createStatement();
            // 5. 执行sql
            res = stmt.executeQuery(sql);
            // 6. 遍历集合，封装对象，装载集合
            Emp emp = null;
            emps = new ArrayList<>();
            while (res.next()) {
                int id = res.getInt("id");
                String ename = res.getString("ename");
                int job_id = res.getInt("job_id");
                int mgr = res.getInt("mgr");
                Date joindate = res.getDate("joindate");
                double salary = res.getDouble("salary");
                double bonus = res.getDouble("bonus");
                int dept_id = res.getInt("dept_id");
                // 创建emp对象,并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                emps.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }




        return emps;

    }
/**
 * @Description  演示JDBC工具类
 * @Author hyr
 * @Date 2019-09-26 10:51
 */
    public List<Emp> findAll2()  {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        ArrayList<Emp> emps = null;
        try {
            //1.创建连接，包含注册驱动
             conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from emp";
            // 4. 获取执行sql的对象
            stmt = conn.createStatement();
            // 5. 执行sql
            res = stmt.executeQuery(sql);
            // 6. 遍历集合，封装对象，装载集合
            Emp emp = null;
            emps = new ArrayList<>();
            while (res.next()) {
                int id = res.getInt("id");
                String ename = res.getString("ename");
                int job_id = res.getInt("job_id");
                int mgr = res.getInt("mgr");
                Date joindate = res.getDate("joindate");
                double salary = res.getDouble("salary");
                double bonus = res.getDouble("bonus");
                int dept_id = res.getInt("dept_id");
                // 创建emp对象,并赋值
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                emps.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(res,stmt,conn);

        }




        return emps;

    }


}
