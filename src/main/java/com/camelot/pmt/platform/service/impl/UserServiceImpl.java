package com.camelot.pmt.platform.service.impl;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import javax.servlet.http.Cookie;

/**
 * 
 * @Title: UserServiceImpl.java
 * @Description: TODO(用户操作实现层)
 * @author: jh
 * @date: 2018年2月5日 下午3:12:12
 */
@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * <p>
     * Description:[新增用户]
     * <p>
     * 
     * @param User
     *            user
     * @return ExecuteResult<User>
     */
    @Override
    public String addUser(User user) {
        // 1.插入用户表
        String userId = UUIDUtil.getUUID();
        user.setUserId(userId);
        String inputPassword = user.getPassword();
        String encryptPassword = new Sha256Hash(inputPassword).toHex();
        user.setPassword(encryptPassword);
        user.setModifyUserId(user.getCreateUserId());
        // 2.插入用户信息表
        // 检查用户名是否存在，不存在的话再插入用户表
        User dbModel = userMapper.findUserByLoginCode(user.getLoginCode());
        if (dbModel != null) {
            return "该用户已经存在！";
        }
        long checkCount = userMapper.checkUserExistByUserJobNum(user.getUserJobNum());
        if (checkCount == 1) {
            return "该用员工号已经存在！";
        }
        userMapper.addUser(user);
        userMapper.addUserInfo(user);
        // 3.如果指定了部门，就插入用户组织表
        if (!StringUtils.isEmpty(user.getOrgId())) {
            userMapper.addUserOrg(user);
        }
        // 4.如果指定了角色，就插入用户角色表
        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
            String[] roleIds = user.getRoleIds();
            for (String roleId : roleIds) {
                user.setRoleId(roleId);
                userMapper.addUserRole(user);
            }
        }
        // 5.通过邮件发送新添加的用户信息
        return "添加用户成功！";
    }

    /**
     * <p>
     * Description:[根据ID删除用户]
     * <p>
     * 
     * @param String
     *            userId
     * @return boolean
     */
    @Override
    public boolean deleteUserByUserId(String userId) {
        int deleteUserByUserIdResult = userMapper.deleteUserByUserId(userId);
        int deleteUserInfoByUserIdResult = userMapper.deleteUserInfoByUserId(userId);
        // 判断用户部门表中间表是否有记录
        long checkUserOrg = userMapper.checkUserOrgExistByUserId(userId);
        if (checkUserOrg != 0) {
            userMapper.deleteUserOrgByUserId(userId);
        }
        long checkUserRole = userMapper.checkUserRoleIsExistByUserId(userId);
        if (checkUserRole != 0) {
            userMapper.deleteUserRoleByUserId(userId);
        }
        if (deleteUserByUserIdResult != 0 && deleteUserInfoByUserIdResult != 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 
     * Description:[查询所有用户]
     * 
     * @return List<User>
     * @author [maple]
     */
    @Override
    public List<User> queryAllUsers() {
        List<User> list = userMapper.queryAllUsers();
        return list;
    }

    /**
     * Description:[根据userId获取单个用户信息]
     * 
     * @param String
     *            userId
     * @return User
     */
    @Override
    public User queryUserByUserId(String userId) {
        return userMapper.queryUserByUserId(userId);
    }

    /**
     * Description:[根据用户登录账号和密码检查用户信息]
     * 
     * @param User
     *            user
     * @return User
     */
    @Override
    public User queryLoginCodeAndPassword(User user) {
        // 1.获取用户输入的登录账号
        String inputLoginCode = user.getLoginCode();
        // 2.根据登录账号去库中获取用户信息,检查用户是否存在
        User dbModel = userMapper.findUserByLoginCode(inputLoginCode);
        if (dbModel == null) {
            return null;
        }
        // 3.记录存在，再检查用的输入密码与库里的密码是否匹配
        String dbPassword = dbModel.getPassword();
        String inputPassword = user.getPassword();
        String encryptPassword = new Sha256Hash(inputPassword).toHex();
        if (!dbPassword.equals(encryptPassword)) {
            return null;
        }
        user.setPassword(dbPassword);
        User reusltUser = userMapper.checkUserLoginCodeAndPassword(user);
        return reusltUser;
    }

    /**
     * 
     * Description:[列表展示用户]
     * 
     * @param UserVo
     *            userVo
     * @return PageInfo
     * @author [maple] 2018年4月13日下午3:15:16
     */
    @Override
    public PageInfo queryUsersList(UserVo userVo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 利用userVo做 条件查询，默认查询所有的
        List<UserVo> usersList = userMapper.selectUsersList(userVo);
        PageInfo pageResult = new PageInfo(usersList);
        pageResult.setList(usersList);
        return pageResult;
    }

    /**
     * 
     * Description:[根据用户ID更新一个用户详情]
     * 
     * @param User
     * @return ExecuteResult<String>
     * @author [maple]
     */
    @Override
    public String modifyUserDetailsByUserId(User user) {
        // 1.判断用户表需要更新的字段
        if (!StringUtils.isEmpty(user.getUsername()) || !StringUtils.isEmpty(user.getLoginCode())
                || !StringUtils.isEmpty(user.getPassword()) || !StringUtils.isEmpty(user.getState())
                || !StringUtils.isEmpty(user.getModifyUserId())) {
            if (!StringUtils.isEmpty(user.getPassword())) {
                String encryptPassword = new Sha256Hash(user.getPassword()).toHex();
                user.setPassword(encryptPassword);
            }
            int updateResult = userMapper.modifyUserByUserId(user);
            if (updateResult == 0) {
                return "更新用户失败！";
            }
        }
        // 2.判断用户信息表更新
        if (!StringUtils.isEmpty(user.getUserPhone()) || !StringUtils.isEmpty(user.getUserMail())) {
            int updateResult = userMapper.modifyUserInfoByUserId(user);
            if (updateResult == 0) {
                return "更新用户失败！";
            }
        }
        // 3.判断用户组织表
        if (!StringUtils.isEmpty(user.getOrgId())) {
            long checkResult = userMapper.checkUserOrgExistByUserId(user.getUserId());
            if (checkResult == 0) {
                user.setCreateUserId(user.getModifyUserId());
                userMapper.addUserOrg(user);
            } else {
                int updateResult = userMapper.modifyUserOrgByUserId(user);
                if (updateResult == 0) {
                    return "更新用户失败！";
                }
            }

        }
        // 4.用户信角色表更新
        if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
            String[] roleIds = user.getRoleIds();
            // 检查用户角色表是否存在该用户
            long checkCount = userMapper.checkUserRoleIsExistByUserId(user.getUserId());
            if (checkCount <= 0) {
                for (String roleId : roleIds) {
                    user.setRoleId(roleId);
                    user.setCreateUserId(user.getModifyUserId());
                    userMapper.addUserRole(user);
                }
            } else {
                // 如果存在该用户，先删除后，再添加一遍
                String userRoleCreateUserId = userMapper.queryUserRoleCreateUserByUserId(user.getUserId());
                userMapper.deleteUserRoleByUserId(user.getUserId());
                user.setCreateUserId(userRoleCreateUserId);
                for (String roleId : roleIds) {
                    user.setRoleId(roleId);
                    userMapper.addUserRole(user);
                }
            }
        }
        return "更新用户成功！";
    }

    /**
     * 
     * Description:[根据用户ID查询一个用户信息详情]
     * 
     * @param String
     *            userId
     * @return ExecuteResult<User>
     * @author [maple]
     */
    @Override
    public User queryUserInfoById(String userId) {
        return userMapper.queryUserInfoById(userId);
    }

    /**
     * 
     * Description:[用户重置密码]
     * 
     * @param User
     *            user
     * @return String
     * @author [maple] 2018年4月16日下午10:44:45
     */
    @Override
    public String resetUserPasswordByUserId(User user) {
        if (StringUtils.isEmpty(user.getPassword())) {
            String random = UUIDUtil.getUUID();
            String generatePassword = random.substring(0, 6);
            String encryptPassword = new Sha256Hash(generatePassword).toHex();
            user.setPassword(encryptPassword);
            int updateResult = userMapper.resetUserPasswordByUserId(user);
            if (updateResult <= 0) {
                return "重置密码失败！";
            }
            return generatePassword;
        }
        String inputPassword = user.getPassword();
        String encryptPassword = new Sha256Hash(inputPassword).toHex();
        user.setPassword(encryptPassword);
        int updateResult = userMapper.resetUserPasswordByUserId(user);
        if (updateResult <= 0) {
            return "重置密码失败！";
        }
        return "重置密码成功！";
    }

    /**
     * 
     * Description:[根据用户名模糊查询获取user对象]
     * 
     * @param String
     *            username
     * @return List<User>
     * @author [maple] 2018年4月18日下午3:49:33
     */
    @Override
    public List<User> queryUsersByUserName(String username) {
        return userMapper.queryUsersByUserName(username);
    }

}
