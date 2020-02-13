
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/*
1、根据xml配置文件(全局配置文件创建一个SqlSessionFactory对象)
 */

public class MybatisTest {
    public void test() throws IOException {
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取sqlSession实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        //第一个参数：sql唯一标识符
        //第二个参数：执行sql语句要用的参数
        //推荐namespace+id
        try {
            Student student = openSession.selectOne("selectStudent",1);
            System.out.println(student);
        }finally {
            openSession.close();
        }
    }

    public static void main(String[] args) throws IOException {
        MybatisTest mybatisTest = new MybatisTest();
        mybatisTest.test();
    }
}
