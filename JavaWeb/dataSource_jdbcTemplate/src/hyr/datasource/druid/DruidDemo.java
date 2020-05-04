package hyr.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //1.导入jar包
        //2.定义配置文件
        //3.加载配置文件
        Properties pro = new Properties();
        pro.load(DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties"));
        //获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //获取连接
        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);
        }

    }
}
