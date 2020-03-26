package com.example.popular.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: FuYu
 * @create: 03/23/2020
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 328831182728243570L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;

}
