package com.example.popular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/div")
    public String div() {
        return "divDemo";
    }

    @GetMapping("/th")
    public String thymeleaf(ModelMap map) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        map.put("list", list);
        return "login";
    }
}
