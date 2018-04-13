package com.camelot.pmt.platform.service;


import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.RoleToUser;
import com.camelot.pmt.platform.model.User;

import java.util.List;

public interface RoleToUserService {

    /**
     * 根据角色绑定用户
     *
     * @param String roleIds, String userIds, String createUserId, String modifyUserId
     * @return ExecuteResult
     */
    ExecuteResult addUserByRole(RoleToUser roleToUser);

    /**
     * 根据角色修改用户
     * 
     * @param String roleIds, String userIds, String createUserId, String modifyUserId
     * @return ExecuteResult
     */
    ExecuteResult updateUserByRole(RoleToUser roleToUser);

    /**
     * 根据角色id查询用户列表
     * 
     * @param String roleId
     * @return ExecuteResult<List<User>>
     */
    ExecuteResult<List<User>> queryUserByRole(RoleToUser role);
}
