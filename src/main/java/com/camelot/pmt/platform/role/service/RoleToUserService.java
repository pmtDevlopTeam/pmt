package com.camelot.pmt.platform.role.service;

import java.util.List;

import com.camelot.pmt.platform.role.model.RoleToUser;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.ExecuteResult;

public interface RoleToUserService {

    /**
     * 根据角色绑定用户
     * 
     * @return
     */
    ExecuteResult addUserByRole(RoleToUser roleToUser);

    /**
     * 根据角色修改用户
     * 
     * @param roleToUser
     * @return
     */
    ExecuteResult updateUserByRole(RoleToUser roleToUser);

    /**
     * 根据角色id查询用户列表
     * 
     * @param role
     * @return
     */
    ExecuteResult<List<UserModel>> queryUserByRole(RoleToUser role);
}
