package com.camelot.pmt.platform.user.service;

import java.util.List;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;

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
     * Description:[保存用户]
     * </p>
     * 
     * @param userModel
     *            void
     * @return ExecuteResult<String>
     * @author [maple]
     */
    ExecuteResult<String> save(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[删除用户]
     * </p>
     * 
     * @param userModel
     *            void
     * @author [maple]
     * @return
     */
    ExecuteResult<String> delete(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[查询用户]
     * </p>
     * 
     * @return List<UserModel>
     * @author [maple]
     */
    ExecuteResult<List<UserModel>> findAllUsers();

    /**
     * 
     * <p>
     * Description:[查询用户]
     * </p>
     * 
     * @return List<UserModel>
     * @author [maple]
     */
    ExecuteResult<UserModel> findUserById(String userId);

    /**
     * 
     * <p>
     * Description:[检查用户登录账号和密码]
     * </p>
     * 
     * @return List<UserModel>
     * @author [maple]
     */
    ExecuteResult<UserModel> queryLoginCodeAndPassword(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[分页查询用户列表]
     * </p>
     * 调用实现类方法:queryWorkbenchTaskList
     * 
     * @param page
     * @return ExecuteResult<DataGrid<UserModel>>
     * @author [maple]
     */
    ExecuteResult<DataGrid<UserModel>> queryUsers(Pager page);

    // /**
    // *
    // *<p>Description:[根据用户ID更新一个用户]</p>
    // * @param userModel
    // * @return ExecuteResult<String>
    // * @author [name]
    // */
    //// ExecuteResult<String> editUserById(UserModel userModel);
    // /**
    // *
    // *<p>Description:[查询用户]</p>
    // * @return List<UserModel>
    // * @author [name]
    // */
    // ExecuteResult<List<UserModel>> findUser();
    //
    // /**
    // *
    // *<p>Description:[分页查询用户列表]</p>
    // *调用实现类方法:queryWorkbenchTaskList
    // * @param page
    // * @return ExecuteResult<DataGrid<UserModel>>
    // * @author [name]
    // */
    // ExecuteResult<DataGrid<UserModel>> queryUsers(Pager page);
    //
    // /**
    // *
    // *<p>Description:[删除用户]</p>
    // * @param userModel void
    // * @author [tongxiying]
    // * @return
    // */
    // ExecuteResult<Long> delete(UserModel userModel);

}
