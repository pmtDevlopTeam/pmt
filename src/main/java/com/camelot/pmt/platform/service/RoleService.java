package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.util.Tree;

/**
 * 角色接口层
 */
public interface RoleService {

    /**
     * 新增角色
     *
     * @param String
     *            parentId, String roleName, String state
     * @return ExecuteResult<Role>
     */
    boolean addRole(Role role) throws Exception;

    /**
     * 删除角色
     *
     * @param String
     *            roleId
     * @return ExecuteResult<Role>
     */
    boolean deleteRoleById(Role role);

    /**
     * 修改角色
     *
     * @param String
     *            roleId, String roleName, String state
     * @return ExecuteResult<Role>
     */
    boolean updateRoleById(Role role);

    /**
     * 查询角色列表
     *
     * @return ExecuteResult<List<Role>>
     */
    List<Tree<Role>> queryAllRole();

    /**
     * 验证角色名称是否可用
     *
     * @param String
     *            roleName
     * @return ExecuteResult<Role>
     */
    boolean getRoleNameVerification(Role role);
}
