package com.example.popular.shiro.util;

import com.example.popular.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: FuYu
 * @create: 03/25/2020
 */
public class ShiroUtils {


    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }


    public static User getUser(){
        User user = null;
        Object obj = getSubject().getPrincipal();
        if (!StringUtils.isEmpty(obj))
        {
            user = new User();
            BeanUtils.copyProperties(obj, user);
        }
        return user;
    }
}
