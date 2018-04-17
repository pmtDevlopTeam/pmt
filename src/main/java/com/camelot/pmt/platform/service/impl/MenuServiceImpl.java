package com.camelot.pmt.platform.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.common.BaseState;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.BuildTree;
import com.camelot.pmt.util.Tree;
import com.camelot.pmt.util.UUIDUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 权限菜单服务接口实现类
 * @author gnerv
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    MenuMapper menuMapper;
    
    @Autowired
    ExecuteResult result;

    @Override
    public ExecuteResult<Menu> createMenu(Menu menu) {
        try {
        	menu = createMenuModel(menu);
            int createMenu = menuMapper.createMenu(menu);
            if (createMenu == 1) {
            	result.setResult(menu);
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Menu> deleteMenuByMenuId(String menuId) {
        try {
            int deleteMenuByMenuId = menuMapper.deleteMenuByMenuId(menuId);
            if (deleteMenuByMenuId == 1) {
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Menu> modifyMenuByMenuId(Menu menu) {
        try {
        	menu = modifyMenuModel(menu);
            int modifyMenuByMenuId = menuMapper.modifyMenuByMenuId(menu);
            if (modifyMenuByMenuId == 1) {
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Menu> queryMenuByMenuId(String menuId) {
        try {
            Menu queryMenuByMenuId = menuMapper.queryMenuByMenuId(menuId);
            if (queryMenuByMenuId != null) {
            	result.setResult(queryMenuByMenuId);
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ExecuteResult<List<Menu>> queryAllMenu() {
        try {
            List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
            List<Tree<Menu>> list = null;
            List<Menu> queryAllMenu = menuMapper.queryAllMenu();
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
                // 默认顶级菜单为０，根据数据库实际情况调整
                list = BuildTree.buildList(trees, "0");
            }
            if (list != null) {
            	result.setResult(list);
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

	@Override
	public ExecuteResult<List<Menu>> querySubMenuByMenuId(String menuId) {
        try {
            List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
            List<Tree<Menu>> list = null;
            List<Menu> queryAllMenu = menuMapper.queryAllMenu();
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
                // 默认顶级菜单为０，根据数据库实际情况调整
                list = BuildTree.buildList(trees, "0");
            }
            if (list != null) {
            	result.setResult(list);
            }else {
            	result.setSuccess(false);
            	return result;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
	}

	@Override
	public Page<Menu> selectMenuPage(Page<Menu> page) {
		return page.setRecords(menuMapper.selectMenuList(page));
	}
	
	public Menu createMenuModel(Menu menu) {
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
	
	public Menu modifyMenuModel(Menu menu) {
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
