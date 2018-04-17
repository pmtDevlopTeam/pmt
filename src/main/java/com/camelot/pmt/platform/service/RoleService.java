package com.camelot.pmt.platform.service;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.util.Tree;

import java.util.List;

/**
 * 角色接口层
 */
public interface RoleService {


    /**
     * 新增角色
     *
     * @param String parentId, String roleName, String state
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> createRole(Role role);

    /**
     * 删除角色
     *
     * @param String roleId
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> deleteRoleById(Role role);

    /**
     * 修改角色
     *
     * @param String roleId, String roleName, String state
     * @return ExecuteResult<Role>
     */
    ExecuteResult<Role> modifyRoleById(Role role);

    /**
     * 查询角色列表
     *
     * @return ExecuteResult<List<Role>>
     */
    ExecuteResult<List<Tree<Role>>> queryAllRole();

    /**
     * 验证角色名称是否可用
     *
     * @param String roleName
     * @return ExecuteResult<Role>
     */
    ExecuteResult getRoleNameVerification(Role role);
}
