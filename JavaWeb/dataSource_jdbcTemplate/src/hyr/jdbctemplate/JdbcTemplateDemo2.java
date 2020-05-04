package hyr.jdbctemplate;

import hyr.domain.Emp;
import hyr.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description     JdbcTemplate的使用
 *       * update():执行DML语句。增、删、改语句
 * 		* queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
 * 			* 注意：这个方法查询的结果集长度只能是1
 * 		* queryForList():查询结果将结果集封装为list集合
 * 			* 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
 * 		* query():查询结果，将结果封装为JavaBean对象
 * 			* query的参数：RowMapper
 * 				* 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
 * 				* new BeanPropertyRowMapper<类型>(类型.class)
 * 		* queryForObject：查询结果，将结果封装为对象
 * 			* 一般用于聚合函数的查询
 * @Author hyr
 * @Version
 * @Date 2019-09-26 10:51
 */
public class JdbcTemplateDemo2 {

    //1.获取JdbcTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 1. 修改1号数据的 salary 为 10000
     */
    @Test
    public void test1(){
        //2. 定义sql
        String sql = "update emp set salary = 10000 where id = 1001";
        //3. 执行sql
        int count = template.update(sql);
        System.out.println(count);
    }
    /**
     * 2. 添加一条记录
     */
    @Test
    public void test2(){
        String sql = "insert into emp(id,ename,dept_id) values(?,?,?)";
        int count = template.update(sql, 1015, "郭靖", 10);
        System.out.println(count);

    }

    /**
     * 3.删除刚才添加的记录
     */
    @Test
    public void test3(){
        String sql = "delete from emp where id = ?";
        int count = template.update(sql, 1015);
        System.out.println(count);
    }

    /**
     * 4.查询id为1001的记录，将其封装为Map集合
     * 注意：这个方法查询的结果集长度只能是1
     */
    @Test
    public void test4(){
        Map<String, Object> map = template.queryForMap("select * from emp where id = ?", 1001);
        System.out.println(map);
        //{id=1001, ename=孙悟空, job_id=4, mgr=1004, joindate=2000-12-17, salary=10000.00, bonus=null, dept_id=20
    }

    /**
     * 5. 查询所有记录，将其封装为List
     */
    @Test
    public void test5(){
        List<Map<String, Object>> list = template.queryForList("select * from emp");
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合.
     * 此处自己定义了匿名RowMapper对象
     */

    @Test
    public void test6(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new RowMapper<Emp>() {

            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                return emp;
            }
        });


        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
            使用默认RowMapper对象
     */

    @Test
    public void test6_2(){
        List<Emp> list = template.query("select * from emp", new BeanPropertyRowMapper<>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }

    }
    /**
     * 7. 查询总记录数
     */

    @Test
    public void test7(){
        Long total = template.queryForObject("select count(id) from emp", Long.class);
        System.out.println(total);
    }
}
