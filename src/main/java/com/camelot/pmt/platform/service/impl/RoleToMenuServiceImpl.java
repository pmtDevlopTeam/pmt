package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.common.Modular;
import com.camelot.pmt.platform.log.LogAspect;
import com.camelot.pmt.platform.mapper.MenuMapper;
import com.camelot.pmt.platform.mapper.RoleMapper;
import com.camelot.pmt.platform.mapper.RoleToMenuMapper;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.service.RoleService;
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

    @Autowired
    private LogAspect logAspect;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 角色绑定权限
     *
     * @param roleToMenu
     * @return
     */
    @Override
    public boolean createRoleToMenu(RoleToMenu roleToMenu) {
        boolean isContains = true;
        String stringBuffer = "";
        List<Role> roleList = roleMapper.queryRoleByroleId(roleToMenu.getRoleId());
        List<String> roleId = Arrays.asList(roleToMenu.getMenuIds());
        for (int i = 0; i < roleId.size(); i++) {
            Menu menu = menuMapper.queryMenuByMenuId(roleId.get(i));
            stringBuffer += menu.getMenuName() + "、";
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
            return true;
        }
        for (String ids : roleToMenu.getMenuIds()) {
            roleToMenu.setMenuId(ids);
            roleToMenuMapper.createRoleToMenu(roleToMenu);
        }
        logAspect.insertBindingLog(stringBuffer, roleList.get(0).getRoleName(), Modular.ROLETOMENU,
                roleToMenu.getCreateUserId());
        return false;
    }

    /**
     * 角色修改绑定权限
     *
     * @param roleToMenu
     * @return
     */
    @Override
    public boolean updateRoleToMenu(RoleToMenu roleToMenu) {
        boolean isContains = true;
        String stringBuffer = "";
        List<Role> roleList = roleMapper.queryRoleByroleId(roleToMenu.getRoleId());
        List<String> roleId = Arrays.asList(roleToMenu.getMenuIds());
        for (int i = 0; i < roleId.size(); i++) {
            Menu menu = menuMapper.queryMenuByMenuId(roleId.get(i));
            stringBuffer += menu.getMenuName() + "、";
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
            return true;
        }
        roleToMenuMapper.deleteRoleToMenu(roleToMenu);

        for (String ids : roleToMenu.getMenuIds()) {
            roleToMenu.setMenuId(ids);
            roleToMenuMapper.createRoleToMenu(roleToMenu);
        }
        logAspect.insertBindingLog(stringBuffer, roleList.get(0).getRoleName(), Modular.ROLETOMENU,
                roleToMenu.getCreateUserId());
        return false;
    }

    /**
     * 根据角色id查询权限菜单
     *
     * @param roleToMenu
     * @return
     */
    @Override
    public List<Menu> selectMenuByRoleId(RoleToMenu roleToMenu) {
        List<RoleToMenu> list = roleToMenuMapper.selectMenuByRoleId(roleToMenu);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Menu> menuList = new ArrayList<Menu>();
        for (RoleToMenu role : list) {
            Menu menu = menuMapper.queryMenuByMenuId(role.getMenuId());
            if (menu != null) {
                menuList.add(menu);
            }
        }
        return menuList;
    }
}
