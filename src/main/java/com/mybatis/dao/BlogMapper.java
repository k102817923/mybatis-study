package com.mybatis.dao;

import com.mybatis.pojo.Blog;

public interface BlogMapper {
    // 添加博客
    int create(Blog blog);
}
