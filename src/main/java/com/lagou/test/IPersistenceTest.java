package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

        /*List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        User all = userDao.findByCondition(user);
        System.out.println(all);

        Integer id = 5;
        user.setId(id);
        user.setUsername("王五");
        int result = userDao.add(user);
        System.out.println("add result = " + result);

        all = userDao.findByCondition(user);
        System.out.println(all);

        user.setUsername("amao");
        result = userDao.update(user);
        System.out.println("update result = " + result);

        all = userDao.findByCondition(user);
        System.out.println(all);

        user.setId(id);
        result = userDao.delete(user);
        System.out.println("delete result = " + result);

    }



}
