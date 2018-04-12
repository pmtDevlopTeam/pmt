package com.camelot.pmt.platform.service;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.util.Tree;

import java.util.List;

/**
 * 角色接口层
 */
public interface RoleService {
    /**
     * 查询角色列表
     * 
     * @return ExecuteResult<List<Role>>
     */
    ExecuteResult<List<Tree<Role>>> queryRoleArray();

    /**
     * 新增角色
     * 
     * @param role
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> addRole(Role role);

    /**
     * 修改角色
     * 
     * @param role
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> editRole(Role role);

    /**
     * 删除角色
     * 
     * @param Role
     * @return
     */
    ExecuteResult<Role> deleteRole(Role role);

    /**
     * 验证角色名称是否可用
     * @param role
     * @return
     */
    ExecuteResult getRoleNameVerification(Role role);
}
