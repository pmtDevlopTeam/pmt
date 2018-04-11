package com.camelot.pmt.platform.mapper;


import com.camelot.pmt.platform.model.RoleToUser;

import java.util.List;

public interface RoleToUserMapper {

    /**
     * 根据角色绑定用户
     */
    void addUserByRole(RoleToUser roleToUser);

    /**
     * 根据角色id删除所有属于此id的用户
     * 
     * @param role
     */
    void deleteUserByRoleId(String role);

    /**
     * 根据角色id查询出用户Id
     * 
     * @param role
     * @return
     */
    List<RoleToUser> queryUserByRole(RoleToUser role);
}
