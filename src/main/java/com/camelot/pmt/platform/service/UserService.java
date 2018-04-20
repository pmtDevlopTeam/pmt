package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @Title: UserService.java
 * @Description: 用户管理的相关业务处理
 * @author: maple
 */
public interface UserService {

    /**
     * 
     * <p>
     * Description:[添加用户]
     * </p>
     * 
     * @param User
     * @return ExecuteResult<String>
     * @author [maple]
     */
    String addUser(User user);

    /**
     * 
     * Description:[根据用户userId删除一个用户]
     * 
     * @param String
     *            userId
     * @return boolean
     * @author [maple] 2018年4月19日上午12:02:32
     */
    boolean deleteUserByUserId(String userId);

    /**
     * 
     * Description:[查询用户]
     * 
     * @return List<User>
     * @author [maple]
     */
    List<User> queryAllUsers();

    /**
     * 
     * Description:[]
     * 
     * @param String
     *            userId
     * @return User
     * @author [maple] 2018年4月13日下午3:13:54
     */
    User queryUserByUserId(String userId);

    /**
     * 
     * Description:[检查登录账号和密码]
     * 
     * @param User
     * @return ExecuteResult<User>
     * @author [maple] 2018年4月13日下午3:14:20
     */
    User queryLoginCodeAndPassword(User user);

    /**
     * 
     * Description:[列表展示用户]
     * 
     * @param UserVo
     *            userVo
     * @return ExecuteResult<List<UserVo>>
     * @author [maple] 2018年4月13日下午3:15:16
     */
    PageInfo queryUsersList(UserVo userVo, int pageNum, int pageSize);

    /**
     * 
     * Description:[根据用户ID更新一个用户详情]
     * 
     * @param User
     * @return String
     * @author [maple]
     */
    String updateUserDetailsByUserId(User user);

    /**
     * 
     * Description:[根据用户ID查询一个用户详情信息]
     * 
     * @param String
     *            userId
     * @return User
     * @author [maple] 2018年4月16日上午10:34:21
     */
    User queryUserInfoById(String userId);

    /**
     * 
     * Description:[用户重置密码]
     * 
     * @param User
     *            user
     * @return String
     * @author [maple] 2018年4月16日下午10:44:45
     */
    String updateResetUserPasswordByUserId(User user);

    /**
     * 
     * Description:[根据用户名模糊查询获取user对象]
     * 
     * @param String
     *            username
     * @return List<User>
     * @author [maple] 2018年4月18日下午3:49:33
     */
    List<User> queryUsersByUserName(String username);

}
