package hyr.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description   JDBC工具类 使用Durid 连接池
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JDBCUtils {

    private  static DataSource ds;

    static {
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description        获取连接池对象
     * @Author hyr
     * @Date 2019-09-26 10:51
     */
    public static DataSource getDatasource(){
        return ds;
    }
    /**
     * @Description       获取连接
     * @Author hyr
     * @Date 2019-09-26 10:51
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
