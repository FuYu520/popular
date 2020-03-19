package com.example.thymeleaf.controller;

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

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        mv.addObject("list", list);
        return mv;
    }
}
