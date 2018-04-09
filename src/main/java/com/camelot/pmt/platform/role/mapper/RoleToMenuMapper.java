package com.camelot.pmt.platform.role.mapper;

import java.util.List;

public interface RoleToMenuMapper {
    
    /**
     * 根据角色 查询角色绑定菜单树
     * @param roleId
     * @return
     */
    List<String> queryMenuByRoleId(String roleId);
}
