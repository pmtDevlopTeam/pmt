package com.camelot.pmt.platform.service;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;

import java.util.List;

public interface RoleToMenuService {

    /**
     * 角色绑定权限
     * @param roleToMenu
     * @return
     */
    ExecuteResult<RoleToMenu> addRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 角色修改权限
     * @param roleToMenu
     * @return
     */
    ExecuteResult updateRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 根据角色id查询权限菜单
     * @param roleToMenu
     * @return
     */
    ExecuteResult<List<Menu>> selectMenuByRoleId(RoleToMenu roleToMenu);
}
