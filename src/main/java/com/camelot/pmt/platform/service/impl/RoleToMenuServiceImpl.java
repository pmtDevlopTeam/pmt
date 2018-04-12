package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.mapper.RoleToMenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.service.RoleToMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleToMenuServiceImpl implements RoleToMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleToMenuServiceImpl.class);

    @Autowired
    private RoleToMenuMapper roleToMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 角色绑定权限
     * @param roleToMenu
     * @return
     */
    @Override
    public ExecuteResult<RoleToMenu> addRoleToMenu(RoleToMenu roleToMenu) {
        ExecuteResult<RoleToMenu> result = new ExecuteResult<RoleToMenu>();
        try {
            boolean isContains = true;
            List<String> roleId = Arrays.asList(roleToMenu.getMenuIds());
            for (int i = 0; i < roleId.size(); i++) {
                Menu menu = menuMapper.queryMenuByMenuId(roleId.get(i));
                    if (!menu.getParentId().equals("0")) {
                        isContains = Arrays.asList(roleToMenu.getMenuIds()).contains(menu.getParentId());
                        if (isContains == true) {
                            isContains = true;
                        } else {
                            isContains = false;
                            break;
                        }
                }
                if (isContains == false) {
                    break;
                }
            }
            if (isContains == false) {
                return result;
            }

            for (String ids : roleToMenu.getMenuIds()){
                roleToMenu.setMenuId(ids);
                roleToMenuMapper.addRoleToMenu(roleToMenu);
            }
            result.setResult(roleToMenu);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 角色修改绑定权限
     * @param roleToMenu
     * @return
     */
    @Override
    public ExecuteResult updateRoleToMenu(RoleToMenu roleToMenu) {
        ExecuteResult result = new ExecuteResult();
        try {
            boolean isContains = true;
            List<String> roleId = Arrays.asList(roleToMenu.getMenuIds());
            for (int i = 0; i < roleId.size(); i++) {
                Menu menu = menuMapper.queryMenuByMenuId(roleId.get(i));
                if (!menu.getParentId().equals("0")) {
                    isContains = Arrays.asList(roleToMenu.getMenuIds()).contains(menu.getParentId());
                    if (isContains == true) {
                        isContains = true;
                    } else {
                        isContains = false;
                        break;
                    }
                }
                if (isContains == false) {
                    break;
                }
            }
            if (isContains == false) {
                return result;
            }
            roleToMenuMapper.deleteRoleToMenu(roleToMenu);
            for (String ids : roleToMenu.getMenuIds()){
                roleToMenu.setMenuId(ids);
                roleToMenuMapper.addRoleToMenu(roleToMenu);
            }
            result.setResult(roleToMenu);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据角色id查询权限菜单
     * @param roleToMenu
     * @return
     */
    @Override
    public ExecuteResult<List<Menu>> selectMenuByRoleId(RoleToMenu roleToMenu) {
        ExecuteResult<List<Menu>> result = new ExecuteResult<List<Menu>>();
        try {
            List<RoleToMenu> list = roleToMenuMapper.selectMenuByRoleId(roleToMenu);
            if(CollectionUtils.isEmpty(list)){
                return result;
            }
            List<Menu> menuList = new ArrayList<Menu>();
            for (RoleToMenu role : list){
                Menu menu = menuMapper.queryMenuByMenuId(role.getMenuId());
                if(menu != null) {
                    menuList.add(menu);
                }
            }
            result.setResult(menuList);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
