package com.camelot.pmt.platform.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.util.BuildTree;
import com.camelot.pmt.platform.common.util.Tree;
import com.camelot.pmt.platform.menu.mapper.MenuMapper;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.role.mapper.RoleToMenuMapper;
import com.camelot.pmt.platform.role.service.RoleToMenuService;

public class RoleToMenuServiceImpl implements RoleToMenuService {

    @Autowired
    RoleToMenuMapper roleToMenuMapper;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public JSONObject queryMenuByRoleId(String roleId) {
        List<String> queryMenuByRoleId = roleToMenuMapper.queryMenuByRoleId(roleId);
        if (queryMenuByRoleId != null) {
            HashMap<String, Menu> hashMap = new HashMap<String, Menu>();
            for (String menuId : queryMenuByRoleId) {
                Menu queryMenuByMenuId = menuMapper.queryMenuByMenuId(menuId);
                hashMap.put(menuId, queryMenuByMenuId);
            }

            List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
            List<Menu> queryAllMenu = menuMapper.queryAllMenu();
            if (queryAllMenu != null) {
                for (Menu menu : queryAllMenu) {
                    Tree<Menu> tree = new Tree<Menu>();
                    tree.setId(menu.getMenuId());
                    tree.setParentId(menu.getParentId());
                    tree.setText(menu.getMenuName());
                    if (hashMap.get(menu.getMenuId()) != null) {
                        tree.setChecked(true);
                    }
                    Map<String, Object> attributes = new HashMap<>(16);
                    attributes.put("url", menu.getMenuURL());
                    attributes.put("permission", menu.getMenuPermission());
                    attributes.put("state", menu.getState());
                    attributes.put("sortNum", menu.getSortNum());
                    attributes.put("icon", menu.getMenuIcon());
                    tree.setAttributes(attributes);
                    trees.add(tree);
                }
                // 默认顶级菜单为０，根据数据库实际情况调整
                List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
                return ApiResponse.success(list);
            }
            return ApiResponse.error();
        }
        return ApiResponse.error();
    }

}
