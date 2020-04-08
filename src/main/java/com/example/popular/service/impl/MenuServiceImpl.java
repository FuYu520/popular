package com.example.popular.service.impl;

import com.example.popular.domain.Menu;
import com.example.popular.domain.User;
import com.example.popular.mapper.MenuMapper;
import com.example.popular.service.MenuService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: FuYu
 * @create: 03/25/2020
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> selectPermsByUserId(Integer userId) {
        return null;
    }

    /**
     * 根据用户查询菜单
     *
     * @param user
     * @return
     */
    @Override
    public List<Menu> selectMenusByUser(User user) {
        List<Menu> menus = Lists.newArrayList();
        //管理员显示所有菜单信息
        if (user.isAdmin()){
            menus = menuMapper.selectMenuNormalAll();
            return getChildPerms(menus, 0);
        } else {
            return null;
        }
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param menus 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<Menu> getChildPerms(List<Menu> menus, int parentId) {
        List<Menu> returnList = Lists.newArrayList();
        for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();){
            Menu menu = iterator.next();
            //1,根据传入的某个父节点ID，遍历该父节点的所有子节点
            if (menu.getParentId() == parentId){

                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        return returnList;
    }

    /**
     *递归列表
     * @param menus
     * @param menu
     */
    private void recursionFn(List<Menu> menus, Menu menu) {
        //得到子节点列表
        List<Menu> childList = getChildList(menus, menu);
        //设置子节点数据
        menu.setChildren(childList);
        //循环子节点
        for (Menu child : childList) {
            //判断child下是否有子节点
            if (hasChild(menus, child)){
                //迭代器向下迭代
                Iterator<Menu> it = childList.iterator();
                //向下有值就返回true
                while (it.hasNext()){
                    Menu n = it.next();
                    //得到子节点进行递归
                    recursionFn(menus, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> menus, Menu menu)
    {
        List<Menu> menuList = Lists.newArrayList();
        Iterator<Menu> iterator = menus.iterator();
        //判断是否还有下值
        while (iterator.hasNext()){
            Menu next = iterator.next();
            //如果某个menu 的parentId == 传入的menu 的id 表示是它的子节点
            if (next.getParentId().intValue() == menu.getMenuId().intValue()){
                //子节点 ++
                menuList.add(next);
            }
        }
        return menuList;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu menu)
    {
        return getChildList(list, menu).size() > 0 ? true : false;
    }
}
