package com.camelot.pmt.platform.service;


import java.util.List;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.User;




/**
 * 
 * @Title:  UserService.java
 * @Description: 用户管理的相关业务处理
 * @author: maple
 */
public interface UserService {
	
	/**
	 * 
	 *<p>Description:[保存用户]</p>
	 * @param User void
	 * @return ExecuteResult<String>
	 * @author [maple]
	 */
    ExecuteResult<String> save(User user);
    
	 /**
	  * 
	  *<p>Description:[删除用户]</p>
	  * @param User void
	  * @author [maple]
	  * @return 
	  */
	 ExecuteResult<String> delete(User user);
	 
	 /**
	  * 
	  *<p>Description:[查询用户]</p>
	  * @return List<User>
	  * @author [maple]
	  */
	 ExecuteResult<List<User>> findAllUsers();
	 
	 
	 /**
	  * 
	  *<p>Description:[查询用户]</p>
	  * @return List<User>
	  * @author [maple]
	  */
	 ExecuteResult<User> findUserById(String userId);
	 
	 /**
	  * 
	  *<p>Description:[检查用户登录账号和密码]</p>
	  * @return List<User>
	  * @author [maple]
	  */
	 ExecuteResult<User> queryLoginCodeAndPassword(User user);
	 
	 /**
	  * 
	  *<p>Description:[分页查询用户列表]</p>
	  *调用实现类方法:queryWorkbenchTaskList
	  * @param page
	  * @return ExecuteResult<DataGrid<User>>
	  * @author [maple]
	  */
	 ExecuteResult<DataGrid<User>> queryUsers(Pager page);
		
//    /**
//	 * 
//	 *<p>Description:[根据用户ID更新一个用户]</p>
//	 * @param User 
//	 * @return ExecuteResult<String>
//	 * @author [name]
//	 */
////    ExecuteResult<String> editUserById(User User);
//    /**
//     * 
//     *<p>Description:[查询用户]</p>
//     * @return List<User>
//     * @author [name]
//     */
//    ExecuteResult<List<User>> findUser();
//    
//    /**
//	 * 
//	 *<p>Description:[分页查询用户列表]</p>
//	 *调用实现类方法:queryWorkbenchTaskList
//	 * @param page
//	 * @return ExecuteResult<DataGrid<User>>
//	 * @author [name]
//	 */
//	ExecuteResult<DataGrid<User>> queryUsers(Pager page);
//
//    /**
//     * 
//     *<p>Description:[删除用户]</p>
//     * @param User void
//     * @author [tongxiying]
//     * @return 
//     */
//    ExecuteResult<Long> delete(User User);




}
