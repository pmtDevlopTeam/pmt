package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;

public interface RoleToMenuService {

    /**
     * 角色绑定权限
     * 
     * @param String
     *            roleId, String menuIds, String createUserId, String modifyUserId
     * @return ExecuteResult<RoleToMenu>
     */
    boolean createRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 角色修改权限
     * 
     * @param String
     *            roleId, String menuIds, String createUserId, String modifyUserId
     * @return ExecuteResult
     */
    boolean updateRoleToMenu(RoleToMenu roleToMenu);

    /**
     * 根据角色id查询权限菜单
     * 
     * @param String
     *            roleId
     * @return ExecuteResult<List<Menu>>
     */
    List<Menu> selectMenuByRoleId(RoleToMenu roleToMenu);
}
