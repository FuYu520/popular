package com.example.popular.controller;

import com.example.popular.common.AjaxResult;
import com.example.popular.domain.User;
import com.example.popular.service.UserSerivce;
import com.example.popular.shiro.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserSerivce userSerivce;

    /*@GetMapping("/")
    @ResponseBody
    @RequiresPermissions("admin:admin:admin")
    public AjaxResult findById(@RequestParam("userId") Integer userId){
        return AjaxResult.success(userSerivce.findById(userId));
    }*/

    @GetMapping("/")
    public String findById(Integer UserId, ModelMap map){
        User user = userSerivce.findById(UserId);
        user.setUserName("小黑");
        map.put("user", user);
        return "index";
    }

}
