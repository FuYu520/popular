package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/")
    public String login(@RequestParam("username")String userName, @RequestParam("password")String password, Model model){
        if (!userName.equalsIgnoreCase("admin") && !password.equalsIgnoreCase("admin")){
            model.addAttribute("message", "用户名或密码错误");
            return "login";
        }
        return "hello";
    }

    @GetMapping("/")
    public String login(){
        return "login";
    }
}
