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
import com.camelot.pmt.platform.common.BaseState;
import com.camelot.pmt.platform.log.LogAspect;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.camelot.pmt.platform.service.MailService;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

import javax.servlet.http.Cookie;

/**
 * 
 * @Title: UserServiceImpl.java
 * @Description: TODO(用户操作实现层)
 * @author: maple
 * @date: 2018年4月5日 下午3:12:12
 */
@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private LogAspect logAspect;

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

        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        // 1.插入用户表
        String userId = UUIDUtil.getUUID();
        user.setUserId(userId);
        String inputPassword = user.getPassword();
        String encryptPassword = "";
        if (StringUtils.isEmpty(inputPassword)) {
            encryptPassword = encryptUserPassword(gereratePassword());
        } else {
            encryptPassword = encryptUserPassword(inputPassword);
        }
        user.setPassword(encryptPassword);
        user.setCreateUserId(loginUser.getUserId());
        user.setModifyUserId(loginUser.getUserId());
        user.setState(BaseState.ONE);
        // 2.插入用户信息表
        // 检查用户名是否存在，不存在的话再插入用户表
        User dbModel = userMapper.queryUserIsExistByLoginCode(user.getLoginCode());
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
        logAspect.insertAddLog(user, "用户", loginUser.getUserId());
        // 5.通过邮件发送新添加的用户信息
        return "添加用户成功！";
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
    public String updateUserDetailsByUserId(User user) {
        // 更新前的用户详情
        UserVo beforeUpdate = userMapper.queryUserDetailsByUserId(user.getUserId()).get(0);
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        user.setModifyUserId(loginUser.getUserId());
        // 1.判断用户表需要更新的字段
        if (!StringUtils.isEmpty(user.getUsername()) || !StringUtils.isEmpty(user.getLoginCode())
                || !StringUtils.isEmpty(user.getState()) || !StringUtils.isEmpty(user.getModifyUserId())) {
            int updateResult = userMapper.updateUserByUserId(user);
            if (updateResult == 0) {
                return "更新用户失败！";
            }
        }
        // 2.判断用户信息表更新
        if (!StringUtils.isEmpty(user.getUserPhone()) || !StringUtils.isEmpty(user.getUserMail())) {
            int updateResult = userMapper.updateUserInfoByUserId(user);
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
                int updateResult = userMapper.updateUserOrgByUserId(user);
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
        // 更新后的用户详情
        UserVo afterUpate = userMapper.queryUserDetailsByUserId(user.getUserId()).get(0);
        logAspect.insertUpdateLog(afterUpate, beforeUpdate, "用户", loginUser.getUserId());
        return "更新用户成功！";
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
    public String updateResetUserPasswordByUserId(User user) {
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        user.setModifyUserId(loginUser.getUserId());
        if (StringUtils.isEmpty(user.getPassword())) {
            String generatePassword = gereratePassword();
            String encryptPassword = encryptUserPassword(generatePassword);
            user.setPassword(encryptPassword);
            int updateResult = userMapper.updateResetUserPasswordByUserId(user);
            if (updateResult <= 0) {
                return "重置密码失败！";
            }
            return generatePassword;
        }
        String inputPassword = user.getPassword();
        String encryptPassword = encryptUserPassword(inputPassword);
        user.setPassword(encryptPassword);
        int updateResult = userMapper.updateResetUserPasswordByUserId(user);
        if (updateResult <= 0) {
            return "重置密码失败！";
        }
        return "重置密码成功！";
    }

    /**
     * 
     * Description:[根据用户userId 修改用户密码]
     * 
     * @param User
     *            use
     * @return String
     * @author [maple] 2018年4月18日下午3:49:33
     */
    @Override
    public String updateUserPasswordByUserId(User user) {
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        user.setModifyUserId(loginUser.getUserId());
        user.setUserId(loginUser.getUserId());
        if (user.getNewPassword().equals(user.getSecondNewPassword())) {
            String toDbPassword = encryptUserPassword(user.getNewPassword());
            user.setPassword(toDbPassword);
            int updateResult = userMapper.updateUserPasswordByUserId(user);
            if (updateResult == 0) {
                return "修改密码失败！";
            }
        } else {
            return "两次新密码输入不一致！";
        }
        return "修改密码成功！";
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
        User dbModel = userMapper.queryUserIsExistByLoginCode(inputLoginCode);
        if (dbModel == null) {
            return null;
        }
        // 3.记录存在，再检查用的输入密码与库里的密码是否匹配
        String dbPassword = dbModel.getPassword();
        String inputPassword = user.getPassword();
        String encryptPassword = encryptUserPassword(inputPassword);
        if (!dbPassword.equals(encryptPassword)) {
            return null;
        }
        user.setPassword(dbPassword);
        User reusltUser = userMapper.checkUserLoginCodeAndPassword(user);
        return reusltUser;
    }

    /**
     * 
     * Description:[列表展示用户用户详情]
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
        List<UserVo> usersList = userMapper.queryUsersList(userVo);
        PageInfo pageResult = new PageInfo(usersList);
        pageResult.setList(usersList);
        return pageResult;
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

    /**
     * 
     * Description:[验证用户旧密码]
     * 
     * @param String
     *            password
     * @return String
     * @author [maple] 2018年4月18日下午3:49:33
     */
    @Override
    public boolean checkOldUserPassword(String password) {
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        // 检查用户输入密码是否与库里的一致
        String dbPassword = userMapper.findUserPasswordByLoginCode(loginUser.getLoginCode());
        String encryptPassword = encryptUserPassword(password);
        return encryptPassword.equals(dbPassword);
    }

    /**
     * 
     * Description:[激活一个用户并发送账号信息到用户邮箱]
     * 
     * @param String
     *            password
     * @return String
     * @author [maple] 2018年4月23日下午2:39:08
     */
    @Override
    public boolean activateUserStateByUserId(User user) {
        // 更新前的用户
        User beforeUpdate = userMapper.queryUserByUserId(user.getUserId());
        // 1.激活用户状态
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        user.setModifyUserId(loginUser.getUserId());
        user.setState(BaseState.ZERO);
        int activateResult = userMapper.updateUserStateByUserId(user);
        User dbUserInfoResult = userMapper.queryUserInfoById(user.getUserId());
        // 2.激活成功后，再重新生成密码，将生成的密码和其他用户信息发送到用户邮箱
        String emaliContent = sendEmaliContent(user);
        mailService.sendSimpleMail(dbUserInfoResult.getUserMail(), "PMT项目管理系统账号激活信息", emaliContent);
        // 更新后的用户
        User afterUpate = userMapper.queryUserByUserId(user.getUserId());
        logAspect.insertUpdateLog(afterUpate, beforeUpdate, "用户", loginUser.getUserId());
        return activateResult == 1 ? true : false;
    }

    /**
     * 
     * Description:[禁用一个用户]
     * 
     * @param String
     *            password
     * @return String
     * @author [maple] 2018年4月23日下午2:39:08
     */
    @Override
    public boolean disableUserStateByUserId(User user) {
        // 更新前的用户
        User beforeUpdate = userMapper.queryUserByUserId(user.getUserId());
        User loginUser = (User) ShiroUtils.getSessionAttribute("user");
        user.setModifyUserId(loginUser.getUserId());
        user.setState(BaseState.ONE);
        int disableResult = userMapper.updateUserStateByUserId(user);
        // 更新后的用户
        User afterUpate = userMapper.queryUserByUserId(user.getUserId());
        logAspect.insertUpdateLog(afterUpate, beforeUpdate, "用户", loginUser.getUserId());
        return disableResult == 1 ? true : false;
    }

    /**
     * 
     * Description:[生成发送邮件的内容]
     * 
     * @param User
     *            user
     * @return String
     * @author [maple] 2018年4月23日下午5:01:44
     */
    public String sendEmaliContent(User user) {
        User dbUserResult = userMapper.queryUserByUserId(user.getUserId());
        String generatePassword = updateResetUserPasswordByUserId(user);
        String content = "尊敬的 " + dbUserResult.getUsername() + ":\n" + "    您好！" + "您的项目管理系统登录的账号为："
                + dbUserResult.getLoginCode() + "， 密码为： " + generatePassword + " \n"
                + "    请妥善保管您的账号信息，如有遗失，请及时更新密码或联系管理员重置您的密码！ \n"
                + "                                                                                        PMT项目团队 ";
        return content;
    }

    /**
     * 
     * Description:[随机生成6位明文密码]
     * 
     * @return String
     * @author [maple] 2018年4月23日下午2:36:12
     */
    public String gereratePassword() {
        String random = UUIDUtil.getUUID();
        String generatePassword = random.substring(0, 6);
        return generatePassword;
    }

    /**
     * 
     * Description:[加密用户明文密码]
     * 
     * @param String
     *            password
     * @return String
     * @author [maple] 2018年4月23日下午2:39:08
     */
    public String encryptUserPassword(String password) {
        return new Sha256Hash(password).toHex();
    }

}
