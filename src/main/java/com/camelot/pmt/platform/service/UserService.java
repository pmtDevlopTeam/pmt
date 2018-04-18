package com.camelot.pmt.platform.service;

import java.util.List;

import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;

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
	ExecuteResult<String> createUser(User user);

	/**
	 * 
	 * Description:[删除用户]
	 * 
	 * @param User
	 * @author [maple]
	 * @return
	 */
	ExecuteResult<String> deleteUserByUserId(String userId);

	/**
	 * 
	 * Description:[查询用户]
	 * 
	 * @return ExecuteResult<List<User>>
	 * @author [maple]
	 */
	ExecuteResult<List<User>> queryAllUsers();

	/**
	 * 
	 * Description:[]
	 * @param String userId
	 * @return ExecuteResult<User>
	 * @author [maple] 2018年4月13日下午3:13:54
	 */
	ExecuteResult<User> findUserByUserId(String userId);

	/**
	 * 
	 * Description:[检查登录账号和密码]
	 * 
	 * @param User
	 *            user
	 * @return ExecuteResult<User>
	 * @author [maple] 2018年4月13日下午3:14:20
	 */
	ExecuteResult<User> queryLoginCodeAndPassword(User user);

//	/**
//	 * 
//	 * Description:[分页查询用户列表]
//	 * 
//	 * @param Pager
//	 *            page
//	 * @return ExecuteResult<DataGrid<User>>
//	 * @author [maple]
//	 */
//	ExecuteResult<DataGrid<User>> queryUsersByPage(Pager page);

	/**
	 * 
	 * Description:[列表展示用户]
	 * 
	 * @param UserVo
	 *            userVo
	 * @return ExecuteResult<List<UserVo>>
	 * @author [maple] 2018年4月13日下午3:15:16
	 */
	ExecuteResult<List<UserVo>> queryUsersList(UserVo userVo);

	/**
	 * 
	 * Description:[根据用户ID更新一个用户详情]
	 * 
	 * @param User
	 * @return ExecuteResult<String>
	 * @author [maple]
	 */
	ExecuteResult<String> modifyUserDetailsByUserId(User user);

	/**
	 * 
	 * Description:[根据用户ID查询一个用户详情信息]
	 * @param String userId
	 * @return ExecuteResult<User>
	 * @author [maple]
	 * 2018年4月16日上午10:34:21
	 */
	ExecuteResult<User> queryUserInfoById(String userId);
	
	/**
	 * 
	 * Description:[用户重置密码]
	 * @param User user
	 * @return ExecuteResult<String>
	 * @author [maple]
	 * 2018年4月16日下午10:44:45
	 */
	ExecuteResult<String> resetUserPasswordByUserId(User user);
    

}
