package com.mybatis.dao;

import com.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 获取全部用户
    List<User> getUserList();

    // 根据 ID 查询用户
    User getUserById(int id);

    // 添加用户
    int create(User user);

    // 修改用户
    int update(User user);

    // 删除用户
    int delete(int id);

    // 根据名称和密码查询用户
    User getUserByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);

    // 根据名称和密码查询用户
    User getUserByNameAndPwdV2(Map<String, Object> map);

    // 根据名称模糊查询用户
    List<User> getUserByKeyword(String keyword);

    // 分页查询用户
    List<User> getUserListByLimit(Map<String, Integer> map);

    // 查询所有用户
    @Select("select * from user")
    List<User> getAllUsers();
}
