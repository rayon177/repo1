package hyr.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class C3P0Demo {

    @Test
    public void testC3P0_1() throws SQLException {
        //1.获取DataSource，使用默认配置
        DataSource ds = new ComboPooledDataSource();

        //2.获取连接

        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + " : " + conn);

        }
    }
    @Test
    public void testC3P0_2() throws SQLException {
        //1.获取DataSource，指定名字的
        DataSource ds = new ComboPooledDataSource("otherc3p0");

        //2.获取连接

        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + " : " + conn);

        }
    }
}

