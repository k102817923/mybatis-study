package com.mybatis.dao;

import com.mybatis.pojo.Blog;
import com.mybatis.util.IDUtils;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BlogMapperTest {
    @Test
    public void initBlog() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            Blog blog = new Blog();
            blog.setId(IDUtils.getUUId());
            blog.setTitle("hello mybatis");
            blog.setAuthor("knan");
            blog.setCreateTime(new Date());
            blog.setViews(9999);
            blogMapper.create(blog);

            blog.setId(IDUtils.getUUId());
            blog.setTitle("hello mybatis2");
            blogMapper.create(blog);


            blog.setId(IDUtils.getUUId());
            blog.setTitle("hello mybatis3");
            blogMapper.create(blog);

            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void queryByIF() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            HashMap<String, Object> map = new HashMap<>();
            map.put("title", "hello mybatis");
            map.put("author", "knan");

            List<Blog> blogs = blogMapper.queryByIF(map);
            System.out.println(blogs);
        } finally {
            sqlSession.close();
        }
    }
}
