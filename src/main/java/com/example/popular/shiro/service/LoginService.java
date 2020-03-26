package com.example.popular.shiro.service;

import com.example.popular.domain.User;
import com.example.popular.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验
 */
@Component
public class LoginService {

    @Autowired
    private UserSerivce userSerivce;

    @Autowired
    private PasswordService passwordService;

    public User login(String userName, String password){
        //根据用户查询，是否存在该用户
        User user = userSerivce.selectUserByLoginName(userName);
        //用户不存在
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        //校验密码
        passwordService.validate(user, password);

        return user;
    }
}
