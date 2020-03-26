package com.example.popular.service;

import com.example.popular.domain.User;

public interface UserSerivce {

    User findById(Integer id);

    User selectUserByLoginName(String userName);
}