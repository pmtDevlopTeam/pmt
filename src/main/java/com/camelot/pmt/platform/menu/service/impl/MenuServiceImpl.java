package com.camelot.pmt.platform.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.util.BuildTree;
import com.camelot.pmt.platform.common.util.Tree;
import com.camelot.pmt.platform.menu.mapper.MenuMapper;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.menu.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public JSONObject createMenu(Menu menu) {
        int createMenu = menuMapper.createMenu(menu);
        if (createMenu == 1) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @Override
    public JSONObject deleteMenuByMenuId(String menuId) {
        int deleteMenuByMenuId = menuMapper.deleteMenuByMenuId(menuId);
        if (deleteMenuByMenuId == 1) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @Override
    public JSONObject modifyMenuByMenuId(Menu menu) {
        int modifyMenuByMenuId = menuMapper.modifyMenuByMenuId(menu);
        if (modifyMenuByMenuId == 1) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @Override
    public JSONObject queryMenuByMenuId(String menuId) {
        Menu queryMenuByMenuId = menuMapper.queryMenuByMenuId(menuId);
        if (queryMenuByMenuId != null) {
            return ApiResponse.success(queryMenuByMenuId);
        }
        return ApiResponse.error();
    }

    @Override
    public JSONObject queryAllMenu() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> queryAllMenu = menuMapper.queryAllMenu();
        if (queryAllMenu != null) {
            for (Menu menu : queryAllMenu) {
                Tree<Menu> tree = new Tree<Menu>();
                tree.setId(menu.getMenuId());
                tree.setParentId(menu.getParentId());
                tree.setText(menu.getMenuName());
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

}
