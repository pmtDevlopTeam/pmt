package com.camelot.pmt.platform.service;


import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.RoleToUser;
import com.camelot.pmt.platform.model.UserModel;

import java.util.List;

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
