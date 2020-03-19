package com.example.thymeleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;
}
