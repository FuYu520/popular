package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.User;

/**
 * 用户，业务层
 */
public interface UserSerivce {

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据登录用户名查询用户信息
     * @param userName
     * @return
     */
    User selectUserByLoginName(String userName);

}
