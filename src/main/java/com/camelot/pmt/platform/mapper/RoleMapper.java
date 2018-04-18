package com.camelot.pmt.platform.mapper;

import com.camelot.pmt.platform.model.Role;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询角色列表集合
     * 
     * @return
     */
    List<Role> queryAllRole();

    /**
     * 新增列表
     * 
     * @param role
     */
    int addRole(Role role);

    /**
     * 修改角色
     * 
     * @param role
     */
    int updateRoleById(Role role);

    /**
     * 删除角色
     * 
     * @param Role
     */
    int deleteRoleById(Role role);

    /**
     * 删除角色权限
     * 
     * @param Role
     */
    void deleteRoleMenuById(Role role);

    /**
     * 根据roleId查询角色
     * 
     * @param role
     * @return
     */
    List<Role> queryRoleByroleId(String role);

    /**
     * 验证角色名称是否存在
     * 
     * @param role
     * @return
     */
    List<Role> getRoleNameVerification(Role role);
}
