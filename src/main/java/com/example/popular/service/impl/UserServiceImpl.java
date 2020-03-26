package com.example.popular.service.impl;

import com.example.popular.domain.User;
import com.example.popular.mapper.UserMapper;
import com.example.popular.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务层处理
 */
@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName) {
        return userMapper.selectUserByLoginName(userName);
    }
}
