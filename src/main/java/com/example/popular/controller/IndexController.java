package com.example.popular.controller;

import com.example.popular.domain.Menu;
import com.example.popular.domain.User;
import com.example.popular.service.MenuService;
import com.example.popular.service.UserSerivce;
import com.example.popular.shiro.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/index")
    @RequiresRoles("admin")
    public ModelAndView index(){
        //取身份信息
        User user = ShiroUtils.getUser();
        Subject subject = ShiroUtils.getSubject();
        //根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", user);
        mv.addObject("menus", menus);
        return mv;
    }
}
