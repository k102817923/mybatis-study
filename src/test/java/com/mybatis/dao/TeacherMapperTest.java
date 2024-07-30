package com.mybatis.dao;

import com.mybatis.pojo.Teacher;
import com.mybatis.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class TeacherMapperTest {
    static Logger logger = Logger.getLogger(TeacherMapper.class);

    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = teacherMapper.getTeacher3(1);
            logger.info("Teacher: " + teacher.toString());
        } finally {
            sqlSession.close();
        }
    }
}
