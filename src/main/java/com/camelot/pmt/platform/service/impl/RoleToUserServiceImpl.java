package com.camelot.pmt.platform.service.impl;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.RoleMapper;
import com.camelot.pmt.platform.mapper.RoleToUserMapper;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.model.RoleToUser;
import com.camelot.pmt.platform.model.UserModel;
import com.camelot.pmt.platform.service.RoleToUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleToUserServiceImpl implements RoleToUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleToUserServiceImpl.class);

    @Autowired
    private RoleToUserMapper roleToUserMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据角色绑定用户
     * 
     * @return
     */
    @Override
    public ExecuteResult addUserByRole(RoleToUser roleToUser) {
        ExecuteResult result = new ExecuteResult();

        try {
            boolean isContains = true;
            List<String> roleId = Arrays.asList(roleToUser.getRoleIds());
            for (int i = 0; i < roleId.size(); i++) {
                List<Role> list = roleMapper.queryRoleByroleId(roleId.get(i));
                for (int j = 0; j < list.size(); j++) {
                    if (!list.get(j).getParentId().equals("0")) {
                        isContains = Arrays.asList(roleToUser.getRoleIds()).contains(list.get(j).getParentId());
                        if (isContains == true) {
                            isContains = true;
                        } else {
                            isContains = false;
                            break;
                        }
                    }
                }
                if (isContains == false) {
                    break;
                }
            }
            if (isContains == false) {
                return result;
            }
            for (String role : roleToUser.getRoleIds()) {
                for (String user : roleToUser.getUserIds()) {
                    roleToUser.setRoleId(role);
                    roleToUser.setUserId(user);
                    long date = new Date().getTime();
                    roleToUser.setCreateTime(new Date(date));
                    roleToUser.setModifyTime(new Date(date));
                    roleToUserMapper.addUserByRole(roleToUser);
                }
            }
            result.setResult(roleToUser);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据角色修改用户
     * 
     * @param roleToUser
     * @return
     */
    @Override
    public ExecuteResult updateUserByRole(RoleToUser roleToUser) {
        ExecuteResult result = new ExecuteResult();
        try {
            boolean isContains = true;
            List<String> roleId = Arrays.asList(roleToUser.getRoleIds());
            for (int i = 0; i < roleId.size(); i++) {
                List<Role> list = roleMapper.queryRoleByroleId(roleId.get(i));
                for (int j = 0; j < list.size(); j++) {
                    if (!list.get(j).getParentId().equals("0")) {
                        isContains = Arrays.asList(roleToUser.getRoleIds()).contains(list.get(j).getParentId());
                        if (isContains == true) {
                            isContains = true;
                        } else {
                            isContains = false;
                            break;
                        }
                    }
                }
                if (isContains == false) {
                    break;
                }
            }
            if (isContains == false) {
                return result;
            }

            List<String> roleIds = Arrays.asList(roleToUser.getRoleIds());
            for (String role : roleIds) {
                roleToUserMapper.deleteUserByRoleId(role);
            }
            for (String role : roleToUser.getRoleIds()) {
                for (String user : roleToUser.getUserIds()) {
                    roleToUser.setRoleId(role);
                    roleToUser.setUserId(user);
                    long date = new Date().getTime();
                    roleToUser.setCreateTime(new Date(date));
                    roleToUser.setModifyTime(new Date(date));
                    roleToUserMapper.addUserByRole(roleToUser);
                }
            }
            result.setResult(roleToUser);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * 根据角色id查询用户列表
     * 
     * @param role
     * @return
     */
    @Override
    public ExecuteResult<List<UserModel>> queryUserByRole(RoleToUser role) {
        ExecuteResult<List<UserModel>> result = new ExecuteResult<List<UserModel>>();
        try {
            List<RoleToUser> list = roleToUserMapper.queryUserByRole(role);
            if (CollectionUtils.isEmpty(list)) {
                return result;
            }
            List<UserModel> userModels = new ArrayList<UserModel>();
            for (RoleToUser roleToUser : list) {
                UserModel userModel = userMapper.selectUserById(roleToUser.getUserId());
                userModels.add(userModel);
            }
            if (CollectionUtils.isEmpty(userModels)) {
                return result;
            }
            result.setResult(userModels);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
