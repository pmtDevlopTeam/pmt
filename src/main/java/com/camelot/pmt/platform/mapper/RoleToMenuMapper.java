package com.camelot.pmt.platform.mapper;

import com.camelot.pmt.platform.model.RoleToMenu;

import java.util.List;

public interface RoleToMenuMapper {

    /**
     * 角色绑定菜单权限
     * @param roleToMenu
     */
    void createRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 清除角色所有权限
     * @param roleToMenu
     */
    void deleteRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 根据角色id查询所有权限菜单
     * @param String roleId
     * @return List<RoleToMenu>
     */
    List<RoleToMenu> selectMenuByRoleId(RoleToMenu roleToMenu);
}
