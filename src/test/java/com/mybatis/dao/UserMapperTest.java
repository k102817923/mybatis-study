package com.mybatis.dao;

import com.mybatis.pojo.User;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    static Logger logger = Logger.getLogger(UserMapperTest.class);

    @Test
    public void test() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(1);
            System.out.println(user);
            // mybatis 的一级缓存验证
            // 一级缓存是 sqlSession 级别的缓存，默认开启，无法关闭
            // 同一次会话期间查询的数据会放在一级缓存中，会话提交或关闭则失效
            // 手动删除缓存
//            sqlSession.clearCache();
            User user2 = userMapper.getUserById(1);
            System.out.println(user2);
            System.out.println(user == user2);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int result = userMapper.create(new User(4, "knan", "123456"));
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            int result = userDao.update(new User(4, "knan2", "654321"));
            User user = userMapper.getUserById(4);
            user.setName("knan");
            user.setPwd("123456");
            int result = userMapper.update(user);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int result = userMapper.delete(4);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserByNameAndPwd("李四", "987654");
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("name", "张三");
            map.put("pwd", "abcdef");
            User user = userMapper.getUserByNameAndPwdV2(map);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            String keyword = "%三%";
            List<User> userList = userMapper.getUserByKeyword(keyword);
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
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Integer> map = new HashMap<>();
            map.put("startIndex", 0);
            map.put("pageSize", 2);
            List<User> userList = userMapper.getUserListByLimit(map);
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void getAllUsers() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 执行 SQL
            // 本质上利用了 jvm 的动态代理机制
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getAllUsers();
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            // 关闭 SqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testCache() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        // UserMapper.xml 设置二级缓存后，不同的 sqlSession 查询相同的数据会命中缓存
        // 只要开启了二级缓存，同一个 Mapper 中的查询，可以在二级缓存中获取
        // 查出的数据默认都先放在一级缓存中，只有会话提交或关闭后，一级缓存中的数据才会转存到二级缓存中
        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();

        User user2 = userMapper2.getUserById(1);
        System.out.println(user2);
        System.out.println(user == user2);
        sqlSession2.close();
    }
}
