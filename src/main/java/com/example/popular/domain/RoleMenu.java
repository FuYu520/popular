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
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -84008967180038487L;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;
}
