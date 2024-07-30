package com.mybatis.dao;

import com.mybatis.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {
    // 获取指定老师，注解查询
    @Select("select * from teacher where id = #{id}")
    Teacher getTeacher(@Param("id") int id);

    // 获取指定老师，查询嵌套
    Teacher getTeacher2(@Param("id") int id);

    // 获取指定老师，结果嵌套
    Teacher getTeacher3(@Param("id") int id);
}
