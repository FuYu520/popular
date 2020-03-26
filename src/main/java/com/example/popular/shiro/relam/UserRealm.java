package com.example.popular.shiro.relam;

import com.example.popular.domain.User;
import com.example.popular.service.MenuService;
import com.example.popular.service.RoleSerivce;
import com.example.popular.shiro.service.LoginService;
import com.example.popular.shiro.util.ShiroUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 *自定义relam 处理登录，权限
 */
@Slf4j(topic = "userRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RoleSerivce roleSerivce;

    @Autowired
    private MenuService menuService;

    /**
     *  授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权");
        User user = ShiroUtils.getUser();
        //角色
        Set<String> roles = Sets.newHashSet();
        //功能
        Set<String> menus = Sets.newHashSet();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //权限 管理员有全部权限
        if (user.isAdmin()){
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            //根据用户id查询权限
            roles = roleSerivce.selectRoleKeys(user.getUserId());
            //根据用户id查询权限
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("认证");
        //得到UsernamePasswordToken 中的userName 和 password，loginController 中存入的
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //登录逻辑
        User user = loginService.login(userToken.getUsername(), new String(userToken.getPassword()));

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, userToken.getPassword(), getName());
        return info;
    }
}
