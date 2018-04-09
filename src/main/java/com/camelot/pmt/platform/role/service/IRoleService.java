package com.camelot.pmt.platform.role.service;

import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.utils.ExecuteResult;

import java.util.List;

/**
 * 角色接口层
 */
public interface IRoleService {
    /**
     * 查询角色列表
     * @return ExecuteResult<List<Role>>
     */
    ExecuteResult<List<Role>> queryRoleArray();

    /**
     * 新增角色
     * @param role
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> addRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> editRole(Role role);

    /**
     * 删除角色
     * @param Role
     * @return
     */
    ExecuteResult<Role> deleteRole(Role role);
}
