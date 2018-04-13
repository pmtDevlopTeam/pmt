package com.camelot.pmt.platform.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.Pager;

/**
 * 
 * @Title: UserMapper.java
 * @Description: TODO(用户mapper层)
 * @author: jh
 * @date: 2018年2月5日 下午2:40:03
 */
@Mapper
public interface UserMapper {
    /**
     * 
     * <p>
     * Description:[添加用户]
     * </p>
     * 
     * @param userModel
     *            void
     * @author [name]
     */
    void insertUser(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[删除用户]
     * </p>
     * 
     * @param userModel
     *            void
     * @author [name]
     */
    void deleteUserById(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[根据登录账号查询用户]
     * </p>
     * 
     * @param username
     * @return UserModel
     * @author [maple]
     */
    UserModel findUserByLoginCode(String loginCode);

    /**
     * 
     * <p>
     * Description:[检查登录的账号密码]
     * </p>
     * 
     * @param username
     * @return UserModel
     * @author [maple]
     */
    UserModel checkUserLoginCodeAndPassword(UserModel userModel);

    /**
     * 
     * <p>
     * Description:[根据登录账号查询用户的密码]
     * </p>
     * 
     * @param loginCode
     * @return String
     * @author [maple]
     */
    String findUserPasswordByLoginCode(String loginCode);

    /**
     * 
     * <p>
     * Description:[根据id查询出用户]
     * </p>
     * 
     * @param userId
     * @return UserModel
     * @author [maple]
     */

    UserModel selectUserById(String userId);

    /**
     * 查询所有用户 不分页
     * 
     * @return List<UserModel>
     */
    List<UserModel> selectUsersAll();

    /**
     * 
     * <p>
     * Description:[查询用户总数量]
     * </p>
     * 
     * @return Long 总数量
     * @author [maple]
     */
    Long queryCount();

    /**
     * 
     * <p>
     * Description:[分页查询用户]
     * </p>
     * 
     * @return List<UserModel>
     * @author [maple]
     */
    List<UserModel> findUsersByPage(@Param(value = "page") Pager page);

    // /**
    // *
    // *<p>Description:[查询用户]</p>
    // * @return List<UserModel>
    // * @author [name]
    // */
    // List<UserModel> findUser();

    // /**
    // *
    // *<p>Description:[更新用户]</p>
    // * @param userModel
    // * @return Integer
    // * @author [name]
    // */
    // Integer updateUserById(UserModel userModel);

    // /**
    // *
    // *<p>Description:[根据用户id查询用户密码]</p>
    // * @param userModel
    // * @return Integer
    // * @author [name]
    // */
    // String findUserPasswordById(UserModel userModel);

    // /**
    // *
    // *<p>Description:[分页查询用户]</p>
    // * @return List<UserModel>
    // * @author [name]
    // */
    // List<UserModel> findUsersByPage(@Param(value = "page") Pager page);
    //
    // /**
    // *
    // *<p>Description:[查询用户总数量]</p>
    // * @return Long 总数量
    // * @author [name]
    // */
    // Long queryCount();
    //

    // /**
    // *
    // *<p>Description:[编辑用户]</p>
    // * @param userModel void
    // * @author [name]
    // */
    // void editUser(UserModel userModel);

    //
    // /**
    // *
    // *<p>Description:[修改密码]</p>
    // * @param userModel
    // * @return UserModel
    // * @author [tongxiying]
    // */
    // Byte editByPassword(UserModel userModel);

}
