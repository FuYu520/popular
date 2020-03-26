package com.example.popular.service;

import com.example.popular.domain.Menu;
import com.example.popular.domain.User;

import java.util.List;
import java.util.Set;

public interface MenuService {
    Set<String> selectPermsByUserId(Integer userId);

    List<Menu> selectMenusByUser(User user);
}
