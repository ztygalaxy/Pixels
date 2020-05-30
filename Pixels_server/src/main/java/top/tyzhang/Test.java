package top.tyzhang;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.tyzhang.Dao.UserDao;
import top.tyzhang.Serivces.UserService;
import top.tyzhang.Entity.User;

import java.io.InputStream;

/**
 * @Description TODO
 * @Author Zhang Tianyu
 * @Date 2020-05-30 13:43
 * @Version 1.0
 **/
public class Test {
  public static void main(String[] args) throws Exception {
//      ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//      UserService as = (UserService) applicationContext.getBean("userService");
//      User user = new User();
//      as.find(user);
      //加载mybatis配置文件
      InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
      //创建SqlSessionFactory对象
      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
      //创建SqlSession对象
      SqlSession session = factory.openSession();
      //获取代理对象
      UserDao accountDao=session.getMapper(UserDao.class);
      System.out.println(accountDao.find(new User("aa","20")));
      session.close();
      in.close();
  }
}