package hyr.jdbctemplate;

import hyr.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description     JdbcTemplate 入门
 *                  封装了获取连接和归还连接的细节，只需要在创建Template对象时传入线程池
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JDBCTemplateDemo1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JdbcTemplate 对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3.调用方法
        int count = template.update("update account set balance = 5000 where id = ?",1);
        System.out.println(count);
    }
}
