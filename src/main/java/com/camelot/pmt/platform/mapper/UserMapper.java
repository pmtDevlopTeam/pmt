package com.camelot.pmt.platform.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;



/**
 * 
 * @Title:  UserMapper.java
 * @Description: TODO(用户mapper层)
 * @author: jh
 * @date:  2018年2月5日 下午2:40:03
 */
@Mapper
public interface UserMapper {
	/**
	 * 
	 * Description:[添加用户]
	 * @param User userModel
	 * @author [maple]
	 */
    void insertUser(User userModel);
    
    /**
	 * 
	 * Description:[插入用户信息表]
	 * @param User userModel
	 * @author [maple]
	 */
    void insertUserInfo(User userModel);
    
    /**
	 * 
	 * Description:[插入用户组织表]
	 * @param User userModel
	 * @author [maple]
	 */
    void insertUserOrg(User userModel);
    
    /**
	 * 
	 * Description:[插入用户角色表]
	 * @param User userModel
	 * @author [maple]
	 */
    void insertUserRole(User userModel);
    
  
    /**
     * 
     * Description:[删除用户]
     * @param userModel void
     * @author [name]
     */
    void deleteUserByUserId(User userModel);
    
    
    /**
     * 
     * Description:[根据登录账号查询用户]
     * @param String loginCode
     * @return User
     * @author [maple]
     */
    User findUserByLoginCode(String loginCode);
    
    /**
     * 
     * Description:[检查登录的账号密码]
     * @param User userModel
     * @return User
     * @author [maple]
     */
    User checkUserLoginCodeAndPassword(User userModel);
    
    /**
     * 
     * Description:[根据登录账号查询用户的密码]
     * @param String loginCode
     * @return String
     * @author [maple]
     */
    String findUserPasswordByLoginCode(String loginCode);
    
    /**
     * 
     * Description:[根据id查询出用户]
     * @param userId
     * @return UserModel
     * @author [maple]
     */
    User selectUserById(String userId);
    
    /**
     * Description:[查询用户不分页]
     * @return List<UserModel>
     */
    List<User> queryAllUsers();
    
    /**
     * 
     * Description:[查询用户总数量]
     * @return Long 总数量
     * @author [maple]
     */
    Long countUser();
    
    /**
     * 
     * Description:[分页查询用户]
     * @return List<UserModel>
     * @author [maple]
     */
    List<User> findUsersByPage(@Param(value = "page") Pager page);
    
    /**
     * 
     * Description:[连表查询获取用户列表]
     * @param UserVo userVo
     * @return  List<UserVo>
     * @author [maple]
     * 2018年4月13日下午3:06:37
     */
    List<UserVo> selectUsersList(UserVo userVo);
    
    
    /**
	 * 
	 * Description:[更新用户表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int modifyUserByUserId(User userModel);
    
    /**
	 * 
	 * Description:[更新用户信息表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int modifyUserInfoByUserId(User userModel);
    
    /**
	 * 
	 * Description:[更新用户部门表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int modifyUserOrgByUserId(User userModel);
    
    /**
   	 * 
   	 * Description:[更新用户角色表]
   	 * @param userModel 
   	 * @return int
   	 * @author [maple]
   	 */
    int modifyUserRoleByUserId(User userModel);
    
    /**
     * 
     * Description:[获取所有的用户信息表 记录]
     * @return List<User>
     * @author [maple]
     * 2018年4月13日下午3:38:28
     */
    List<User> queryAllUserInfo();
    
    /**
     * 
     * Description:[根据用户userId 获取用户信息记录]
     * @param 
     * @return 
     * @author [maple]
     * 2018年4月13日下午3:39:51
     */
    User queryUserInfoById(String userId);

   
}
