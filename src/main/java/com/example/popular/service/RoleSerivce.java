package com.example.popular.service;

import java.util.Set;

public interface RoleSerivce {
    Set<String> selectRoleKeys(Integer userId);
}
