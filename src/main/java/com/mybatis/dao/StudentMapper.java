package com.mybatis.dao;

import com.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 获取全部学生，查询嵌套
    List<Student> getStudents();

    // 获取全部学生，结果嵌套
    List<Student> getStudents2();
}
