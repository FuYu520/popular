package com.example.popular.controller;

import com.example.popular.common.AjaxResult;
import com.example.popular.service.UserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {

    @Autowired
    UserSerivce userSerivce;

    /**
     * 登录接口
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestParam("username")String userName, @RequestParam("password")String password){
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        //得到subject及创建用户名/密码身份验证 Token (即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            return AjaxResult.success();
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return AjaxResult.error("用户或密码错误");
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
