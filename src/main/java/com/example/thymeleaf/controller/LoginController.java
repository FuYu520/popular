package com.example.thymeleaf.controller;

import com.example.thymeleaf.common.AjaxResult;
import com.example.thymeleaf.service.UserSerivce;
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
public class LoginController {

    @Autowired
    UserSerivce userSerivce;

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestParam("username")String userName, @RequestParam("password")String password, Model model){
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//        Subject subject = SecurityUtils.getSubject();
//        try{
//            subject.login(token);
//            return AjaxResult.success();
//        }catch (RuntimeException e){
//            return AjaxResult.error("用户或密码错误");
//        }
        return AjaxResult.success();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user")
    public AjaxResult findById(Integer id){
        return AjaxResult.success(userSerivce.findById(id));
    }
}
