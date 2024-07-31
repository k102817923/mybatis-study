package com.mybatis.dao;

import com.mybatis.pojo.Blog;
import com.mybatis.util.IDUtils;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void queryByForEach() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

            HashMap<String, Object> map = new HashMap<>();
            List<Integer> ids = new ArrayList<>();
            ids.add(1);
            ids.add(2);
            ids.add(3);
            map.put("ids", ids);

            List<Blog> blogs = blogMapper.queryByForEach(map);
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        } finally {
            sqlSession.close();
        }
    }
}
