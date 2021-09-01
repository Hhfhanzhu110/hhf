package com.hxy.yeb.common.utils;

import com.hxy.yeb.domain.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class TreeUtil {

    /**
     * 根据父节点将菜单集合转换成树结构
     * @param menus
     * @return
     */
    public static List<Menu> menuListToTree(List<Menu> menus, Long parentId){
        List<Menu> newMenus = new ArrayList<>();
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu mu = iterator.next();
            if (mu.getParentId() == parentId){
                List<Menu> childList = getChildList(mu, menus);
                if (childList.size()>0){
                    childList = menuListToTree(childList,mu.getMenuId());
                }
                mu.setChildren(childList);
                newMenus.add(mu);
            }
        }
        return newMenus;
    }

    /**
     * 获取子节点
     * @param menu
     * @param menus
     * @return
     */
    private static List<Menu> getChildList(Menu menu, List<Menu> menus) {
        List<Menu> childList = new ArrayList<>();
        for(Menu mu : menus){
            if (mu.getParentId()!=null && mu.getParentId() == menu.getMenuId()){
                childList.add(mu);
            }
        }
        return childList;
    }
}
