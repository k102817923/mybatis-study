package com.mybatis.dao;

import com.mybatis.pojo.User;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void test() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> userList = userDao.getUserList();

            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserById() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.getUserById(1);
            System.out.println(user);
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    // 增删改需要提交事务
    @Test
    public void create() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            int result = userDao.create(new User(4, "knan", "123456"));
            System.out.println(result);
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void update() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
//            int result = userDao.update(new User(4, "knan2", "654321"));
            User user = userDao.getUserById(4);
            user.setName("knan");
            user.setPwd("123456");
            int result = userDao.update(user);
            System.out.println(result);
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void delete() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            int result = userDao.delete(4);
            System.out.println(result);
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserByNameAndPwd() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = userDao.getUserByNameAndPwd("李四", "987654");
            System.out.println(user);
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserByNameAndPwdV2() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "张三");
            map.put("pwd", "abcdef");
            User user = userDao.getUserByNameAndPwdV2(map);
            System.out.println(user);
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getUserByKeyword() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            String keyword = "%三%";
            List<User> userList = userDao.getUserByKeyword(keyword);
            for (User user : userList) {
                System.out.println(user);
            }
            sqlSession.commit();
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testLog4J() {
        logger.info("info 方法");
        logger.debug("debug 方法");
        logger.error("error 方法");
    }

    @Test
    public void getUserListByLimit() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("startIndex", 0);
            map.put("pageSize", 2);
            List<User> userList = userDao.getUserListByLimit(map);
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }
}
