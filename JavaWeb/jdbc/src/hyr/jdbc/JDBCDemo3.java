package hyr.jdbc;

import hyr.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description * 事务操作
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JDBCDemo3 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //2.定义sql语句
            //2.1  转出
            pstmt1 = conn.prepareStatement(
                    "update account set balance = balance - ? where id = ?");
            //2.2转入
            pstmt2 = conn.prepareStatement(
                    "update account set balance = balance + ? where id = ?");
            //3.设置参数
            pstmt1.setDouble(1, 500);
            pstmt1.setInt(2, 1);
            pstmt2.setDouble(1, 500);
            pstmt2.setInt(2, 2);

            //4.执行sql
            pstmt1.executeUpdate();
            //制造异常
//            int i = 1 / 0;
            pstmt2.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt1, conn);
            JDBCUtils.close(pstmt2, null);
        }

    }
}
