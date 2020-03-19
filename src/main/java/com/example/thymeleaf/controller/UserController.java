package com.example.thymeleaf.controller;

import com.example.thymeleaf.common.AjaxResult;
import com.example.thymeleaf.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserSerivce userSerivce;

    @GetMapping("/")
    @ResponseBody
    public AjaxResult findById(@RequestParam("userId") Integer userId){
        return AjaxResult.success(userSerivce.findById(userId));
    }

}
