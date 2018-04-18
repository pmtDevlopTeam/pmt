package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.BaseState;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.BuildTree;
import com.camelot.pmt.util.Tree;
import com.camelot.pmt.util.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

	public final static String PARENTID = "0";
	
    @Autowired
    MenuMapper menuMapper;
    
    @Override
    public boolean addMenu(Menu menu) {
    	menu = createMenuModel(menu);
        return menuMapper.addMenu(menu) == 1?true:false;
    }

    @Override
    public boolean deleteMenuByMenuId(String menuId) {
        return menuMapper.deleteMenuByMenuId(menuId) == 1?true:false;
    }

    @Override
    public boolean updateMenuByMenuId(Menu menu) {
    	menu = updateMenuModel(menu);
        return menuMapper.updateMenuByMenuId(menu) == 1?true:false;
    }

    @Override
    public Menu queryMenuByMenuId(String menuId) {
        return menuMapper.queryMenuByMenuId(menuId);
    }

    @Override
    public List<Tree<Menu>> queryAllMenuList() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Tree<Menu>> list = null;
        List<Menu> queryAllMenu = menuMapper.queryAllMenuList();
        if (queryAllMenu != null) {
            for (Menu menu : queryAllMenu) {
                Tree<Menu> tree = new Tree<Menu>();
                tree.setId(menu.getMenuId());
                tree.setParentId(menu.getParentId());
                tree.setText(menu.getMenuName());
                Map<String, Object> attributes = new HashMap<>(16);
                attributes.put("url", menu.getMenuUrl());
                attributes.put("permission", menu.getMenuPermission());
                attributes.put("state", menu.getState());
                attributes.put("sortNum", menu.getSortNum());
                attributes.put("icon", menu.getMenuIcon());
                tree.setAttributes(attributes);
                trees.add(tree);
            }
            list = BuildTree.buildList(trees, PARENTID);
        }
        return list;
    }

	@Override
	public List<Tree<Menu>> querySubMenuListByMenuId(String menuId) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Tree<Menu>> list = null;
        List<Menu> queryAllMenu = menuMapper.queryAllMenuList();
        if (queryAllMenu != null) {
            for (Menu menu : queryAllMenu) {
                Tree<Menu> tree = new Tree<Menu>();
                tree.setId(menu.getMenuId());
                tree.setParentId(menu.getParentId());
                tree.setText(menu.getMenuName());
                Map<String, Object> attributes = new HashMap<>(16);
                attributes.put("url", menu.getMenuUrl());
                attributes.put("permission", menu.getMenuPermission());
                attributes.put("state", menu.getState());
                attributes.put("sortNum", menu.getSortNum());
                attributes.put("icon", menu.getMenuIcon());
                tree.setAttributes(attributes);
                trees.add(tree);
            }
            list = BuildTree.buildList(trees, menuId);
        }
        return list;
	}

	private Menu createMenuModel(Menu menu) {
		menu.setMenuId(UUIDUtil.getUUID());
        if (menu.getState() != null) {
        } else {
        	menu.setState(BaseState.ZERO);
        }
		long date = new Date().getTime();
		menu.setCreateTime(new Date(date));
		menu.setModifyTime(new Date(date));
		User user = (User) ShiroUtils.getSessionAttribute("user");
		if(user != null) {
			menu.setCreateUserId(user.getUserId());
			menu.setModifyUserId(user.getUserId());
		}
		return menu;
	}
	
	private Menu updateMenuModel(Menu menu) {
		menu.setMenuId(UUIDUtil.getUUID());
        if (menu.getState() != null) {
        } else {
        	menu.setState(BaseState.ZERO);
        }
		long date = new Date().getTime();
		menu.setModifyTime(new Date(date));
		User user = (User) ShiroUtils.getSessionAttribute("user");
		if(user != null) {
			menu.setModifyUserId(user.getUserId());
		}
		return menu;
	}

}
