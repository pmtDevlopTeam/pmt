package com.camelot.pmt.platform.service;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.RoleToMenu;

public interface RoleToMenuService {

    /**
     * 角色绑定权限
     * @param roleToMenu
     * @return
     */
    ExecuteResult<RoleToMenu> addRoleToMenu(RoleToMenu roleToMenu);
}
