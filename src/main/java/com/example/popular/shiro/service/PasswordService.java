package com.example.popular.shiro.service;

import com.example.popular.domain.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Component;

/**
 * 登录密码校验
 */
@Component
public class PasswordService {

    /**
     * 密码校验
     * @param user
     * @param password
     */
    public void validate(User user, String password){
        //用户密码 和 输入后加密的密码进行比较
        boolean result = user.getPassword().equals(compare(user.getUserName(), password, user.getSalt()));
        if (!result){
            throw new RuntimeException("密码错误");
        }
    }

    public String compare(String userName, String password, String salt){
        return new Sha256Hash(userName + password + salt).toHex().toString();
    }

}
