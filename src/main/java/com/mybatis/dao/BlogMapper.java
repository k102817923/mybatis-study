package com.mybatis.dao;

import com.mybatis.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    // 添加博客
    int create(Blog blog);

    // 查询博客
    List<Blog> queryByIF(Map<String, Object> map);

    // 查询博客
    List<Blog> queryByForEach(Map<String, Object> map);
}
