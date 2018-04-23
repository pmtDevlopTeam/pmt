package com.camelot.pmt.platform.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;



/**
 * 
 * @Title:  UserMapper.java
 * @Description: 用户mapper
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
    void addUser(User userModel);
    
    /**
	 * 
	 * Description:[插入用户信息表]
	 * @param User userModel
	 * @author [maple]
	 */
    void addUserInfo(User userModel);
    
    /**
	 * 
	 * Description:[插入用户组织表]
	 * @param User userModel
	 * @author [maple]
	 */
    void addUserOrg(User userModel);
    
    /**
	 * 
	 * Description:[插入用户角色表]
	 * @param User userModel
	 * @author [maple]
	 */
    void addUserRole(User userModel);
    
  
    /**
     * 
     * Description:[删除用户]
     * @param userModel void
     * @author [name]
     */
    int deleteUserByUserId(String userId);
    
    
    /**
     * 
     * Description:[根据登录账号查询用户]
     * @param String loginCode
     * @return User
     * @author [maple]
     */
    User queryUserIsExistByLoginCode(String loginCode);
    
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
    User queryUserByUserId(String userId);
    
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
     * Description:[连表查询获取用户列表]
     * @param UserVo userVo
     * @return  List<UserVo>
     * @author [maple]
     * 2018年4月13日下午3:06:37
     */
    List<UserVo> queryUsersList(UserVo userVo);
    
    
    /**
	 * 
	 * Description:[更新用户表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int updateUserByUserId(User userModel);
    
    /**
	 * 
	 * Description:[更新用户信息表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int updateUserInfoByUserId(User userModel);
    
    /**
	 * 
	 * Description:[更新用户部门表]
	 * @param userModel 
	 * @return int
	 * @author [maple]
	 */
    int updateUserOrgByUserId(User userModel);
    
    /**
   	 * 
   	 * Description:[更新用户角色表]
   	 * @param userModel 
   	 * @return int
   	 * @author [maple]
   	 */
    int updateUserRoleByUserId(User userModel);
    
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
    
    
    /**
     * 
     * Description:[根据userId 删除用户部门表记录]
     * @param String userId
     * @return int
     * @author [maple]
     * 2018年4月16日下午4:05:53
     */
    int deleteUserInfoByUserId(String userId);
    
    /**
     * 
     * Description:[根据userId 删除用户部门表记录]
     * @param String userId
     * @return int
     * @author [maple]
     * 2018年4月16日下午4:05:53
     */
    int deleteUserOrgByUserId(String userId);
    
    /**
     * 
     * Description:[根据userId 删除用户角色中间表记录]
     * @param String userId
     * @return int
     * @author [maple]
     * 2018年4月16日下午4:05:53
     */
    int deleteUserRoleByUserId(String userId);
    
    /**
     * 
     * Description:[根据userId 查找用户角色中间表userId是否存在]
     * @param String userId
     * @return long
     * @author [maple]
     * 2018年4月16日下午4:05:53
     */
    long checkUserRoleIsExistByUserId(String userId);
    
    /**
     * 
     * Description:[根据用户UserId重置密码]
     * @param User userModel
     * @return int
     * @author [maple]
     * 2018年4月16日下午10:42:35
     */
    int updateResetUserPasswordByUserId(User userModel);
    
    /**
     * 
     * Description:[根据用户UserId修改用户密码]
     * @param User userModel
     * @return int
     * @author [maple]
     * 2018年4月16日下午10:42:35
     */
    int updateUserPasswordByUserId(User userModel);
    
    /**
     * 
     * Description:[根据userJobNum判断用户是否存在]
     * @param String userJobNum
     * @return long
     * @author [maple]
     * 2018年4月17日下午2:43:13
     */
    long checkUserExistByUserJobNum(String userJobNum);
    
    /**
     * 
     * Description:[获取用户角色中间表的create_user_id]
     * @param String userId
     * @return String
     * @author [maple]
     * 2018年4月17日下午3:08:17
     */
    String queryUserRoleCreateUserByUserId(String userId);

    /**
     *
     * Description:[根据userId判断用户组织表用户是否存在]
     * @param String userJobNum
     * @return long
     * @author [maple]
     * 2018年4月17日下午2:43:13
     */
    long checkUserOrgExistByUserId(String userId);

    /**
     * 
     * Description:[根据用户名模糊查询获取用户对象]
     * @return List<User>
     * @author [maple]
     * 2018年4月13日下午3:38:28
     */
    List<User> queryUsersByUserName(String username);

}
