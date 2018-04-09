package com.camelot.pmt.platform.role.service;


import com.alibaba.fastjson.JSONObject;

public interface IRoleToMenuService {
    
    /**
     * 根据角色 查询角色绑定菜单树
     * @param roleId
     * @return
     */
	JSONObject queryMenuByRoleId(String roleId);
}
