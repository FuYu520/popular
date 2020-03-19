package com.example.thymeleaf.mapper;

import com.example.thymeleaf.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表 数据层
 */
@Repository
public interface UserMapper {

    User findById(Integer id);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    User selectUserByLoginName(@Param("name") String userName);
}
