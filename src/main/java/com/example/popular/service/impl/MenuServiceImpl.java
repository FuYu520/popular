package com.example.popular.service.impl;

import com.example.popular.domain.Menu;
import com.example.popular.domain.User;
import com.example.popular.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: FuYu
 * @create: 03/25/2020
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public Set<String> selectPermsByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<Menu> selectMenusByUser(User user) {
        return null;
    }
}
