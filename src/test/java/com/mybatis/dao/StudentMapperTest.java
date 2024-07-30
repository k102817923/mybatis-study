package com.mybatis.dao;

import com.mybatis.pojo.Student;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class StudentMapperTest {
    static Logger logger = Logger.getLogger(StudentMapper.class);

    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> students = studentMapper.getStudents2();
            for (Student student : students) {
                logger.info("Student: " + student.toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}
