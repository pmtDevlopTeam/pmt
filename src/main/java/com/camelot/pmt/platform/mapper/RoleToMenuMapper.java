package com.camelot.pmt.platform.mapper;

import com.camelot.pmt.platform.model.RoleToMenu;

public interface RoleToMenuMapper {

    /**
     * 角色绑定菜单权限
     * @param roleToMenu
     */
    void addRoleToMenu(RoleToMenu roleToMenu);
}
