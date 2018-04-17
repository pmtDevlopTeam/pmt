package com.camelot.pmt.platform.service.impl;


import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.camelot.pmt.platform.service.UserService;
import com.camelot.pmt.util.UUIDUtil;

import java.util.List;

/**
 * 
 * @Title:  UserServiceImpl.java
 * @Description: TODO(用户操作实现层)
 * @author: jh
 * @date:  2018年2月5日 下午3:12:12
 */
@Service
@Primary
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService{


	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Autowired
    private UserMapper userMapper;
    

    /**
     * <p>Description:[新增用户]<p>
     * @param User user
     * @return ExecuteResult<User>
     */
    @Override
    public ExecuteResult<String> createUser(User user) {
    	ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(user == null){
				result.addErrorMessage("传入的用户实体有误!");
				return result;
			}
			//1.插入用户表
			String userId = UUIDUtil.getUUID();
			user.setUserId(userId);
			String inputPassword = user.getPassword();
			String encryptPassword = new Sha256Hash(inputPassword).toHex();
			user.setPassword(encryptPassword);
			user.setModifyUserId(user.getCreateUserId());
			//2.插入用户信息表
			  //检查用户名是否存在，不存在的话再插入用户表
			User dbModel = userMapper.findUserByLoginCode(user.getLoginCode());
			if(dbModel != null) {
				result.setResult("该用户已经存在！");
				return result;
			}
			long checkCount = userMapper.checkUserExistByUserJobNum(user.getUserJobNum());
			if(checkCount == 1) {
				result.setResult("该员工号已经存在！");
				return result;
			}
			userMapper.insertUser(user);
			userMapper.insertUserInfo(user);
			//3.如果指定了部门，就插入用户组织表
			if(!StringUtils.isEmpty(user.getOrgId())) {
				userMapper.insertUserOrg(user);
			}
			//4.如果指定了角色，就插入用户角色表
			if(user.getRoleIds() != null && user.getRoleIds().length != 0) {
				String[] roleIds = user.getRoleIds();
				for (String roleId : roleIds) {
					user.setRoleId(roleId);
					userMapper.insertUserRole(user);
				}
			}
			//5.通过邮件发送新添加的用户信息
			result.setResult("添加用户成功!");
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
    }


    /**
     * <p>Description:[根据ID删除用户]<p>
     * @param User user
     * @return ExecuteResult<String>
     */
	@Override
	public ExecuteResult<String> deleteUserByUserId(User user) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		userMapper.deleteUserByUserId(user);
    		result.setResult("删除用户成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
        return result;
	}


	/**
	 * 
	 * Description:[查询所有用户]
	 * @return ExecuteResult<List<User>>
	 * @author [maple]
	 */
	@Override
	public ExecuteResult<List<User>> queryAllUsers() {
    	ExecuteResult<List<User>> result = new ExecuteResult<List<User>>();
    	try {
    		List<User> list = userMapper.queryAllUsers();
    		if(list.size() <= 0) {
				return result;
			}
    		result.setResult(list);
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
       return result;
	}


	/**
     * Description:[根据userId获取单个用户信息]
     * @param String userId
     * @return ExecuteResult<User>
     */
	@Override
	public ExecuteResult<User> findUserByUserId(String userId) {
		ExecuteResult<User> result = new ExecuteResult<User>();
		try {
			if(!"".equals(userId) && userId != null) {
				User queryResult = userMapper.selectUserById(userId);
				result.setResult(queryResult);
				return result;
			}
			result.addErrorMessage("查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
     * Description:[根据用户登录账号和密码检查用户信息]
     * @param User user
     * @return ExecuteResult<User>
     */
	@Override
	public ExecuteResult<User> queryLoginCodeAndPassword(User user) {
		ExecuteResult<User> result = new ExecuteResult<User>();
		try {
			if(user != null && !"".equals(user.getLoginCode()) && user.getLoginCode() != null ) {
				//1.获取用户输入的登录账号
				String inputLoginCode = user.getLoginCode();
				//2.根据登录账号去库中获取用户信息,检查用户是否存在
				User dbModel = userMapper.findUserByLoginCode(inputLoginCode);
				if(dbModel == null) {
					result.setResultMessage("该用户不存在！");
					return result;
				}
				//3.记录存在，再检查用的输入密码与库里的密码是否匹配 
				String dbPassword = dbModel.getPassword();
				String inputPassword = user.getPassword();
				String encryptPassword = new Sha256Hash(inputPassword).toHex();
				if(!dbPassword.equals(encryptPassword)) {
					result.setResultMessage("密码不正确!");
					return result;
				}
				user.setPassword(dbPassword);
				User reusltUser = userMapper.checkUserLoginCodeAndPassword(user);
				result.setResult(reusltUser);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}


//	/**
//     * Description:[分页查询用户列表]
//     * @param page
//     * @return ExecuteResult<DataGrid<User>>
//     */
//	@Override
//	public ExecuteResult<DataGrid<User>> queryUsersByPage(Pager page) {
//		ExecuteResult<DataGrid<User>> result = new ExecuteResult<DataGrid<User>>();
//		try{
//            List<User> list = userMapper.findUsersByPage(page);
//            //如果没有查询到数据，不继续进行
//            if (CollectionUtils.isEmpty(list)) {
//            	DataGrid<User> dg = new DataGrid<User>();
//            	result.setResult(dg);
//                return result;
//            }            
//            DataGrid<User> dg = new DataGrid<User>();
//            dg.setRows(list);
//            //查询总条数
//            Long total = userMapper.countUser();
//            dg.setTotal(total);				
//            result.setResult(dg);
//		}catch(Exception e){
//			throw new RuntimeException(e);
//		}
//		return result;
//	}


	/**
	  * 
	  * Description:[列表展示用户]
	  * @param UserVo userVo
	  * @return ExecuteResult<List<UserVo>>
	  * @author [maple]
	  * 2018年4月13日下午3:15:16
	  */
	@Override
	public ExecuteResult<List<UserVo>> queryUsersList(UserVo userVo) {
		ExecuteResult<List<UserVo>> result = new ExecuteResult<List<UserVo>>();
    	try {
    		//利用userVo做 条件查询，默认查询所有的
    		List<UserVo> usersList = userMapper.selectUsersList(userVo);
    		if(usersList.size() <= 0) {
				return result;
			}
    		result.setResult(usersList);
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
       return result;
	}

	/**
	 * 
	 * Description:[根据用户ID更新一个用户详情]
	 * @param User 
	 * @return ExecuteResult<String>
	 * @author [maple]
	 */
	@Override
	public ExecuteResult<String> modifyUserDetailsByUserId(User user) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			//1.判断用户表需要更新的字段
			if(!StringUtils.isEmpty(user.getUsername()) || !StringUtils.isEmpty(user.getLoginCode()) || !StringUtils.isEmpty(user.getPassword()) || !StringUtils.isEmpty(user.getState()) || !StringUtils.isEmpty(user.getModifyUserId())){
				if(!StringUtils.isEmpty(user.getPassword())) {
					String encryptPassword = new Sha256Hash(user.getPassword()).toHex();
					user.setPassword(encryptPassword);
				}
				int updateResult = userMapper.modifyUserByUserId(user);
				if(updateResult == 0) {
					result.addErrorMessage("更新用户失败！");
					return result;
				}
			}
			//2.判断用户信息表更新
			if(!StringUtils.isEmpty(user.getUserPhone()) || !StringUtils.isEmpty(user.getUserMail()) ) {
				int updateResult = userMapper.modifyUserInfoByUserId(user);
				if(updateResult == 0) {
					result.addErrorMessage("更新用户失败！");
					return result;
				}
			}
			//3.判断用户组织表
			if(!StringUtils.isEmpty(user.getOrgId())){
				int updateResult = userMapper.modifyUserOrgByUserId(user);
				if(updateResult == 0) {
					result.addErrorMessage("更新用户失败！");
					return result;
				}
			}
			//4.用户信角色表更新
			if (user.getRoleIds() != null && user.getRoleIds().length != 0) {
				String[] roleIds = user.getRoleIds();
				// 检查用户角色表是否存在该用户
				long checkCount = userMapper.checkUserRoleIsExistByUserId(user.getUserId());
				if (checkCount < 0) {
					for (String roleId : roleIds) {
						user.setRoleId(roleId);
						userMapper.insertUserRole(user);
					}
				} else {
					// 如果存在该用户，先删除后，再添加一遍
					String userRoleCreateUserId = userMapper.queryUserRoleCreateUserByUserId(user.getUserId());
					userMapper.deleteUserRoleByUserId(user.getUserId());
					user.setCreateUserId(userRoleCreateUserId);
					for (String roleId : roleIds) {
						user.setRoleId(roleId);
						userMapper.insertUserRole(user);
					}
				}
			}
			result.setResult("更新用户成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
       return result;
	}

	
	/**
	 * 
	 * Description:[根据用户ID更新一个用户信息详情]
	 * @param String userId 
	 * @return ExecuteResult<User>
	 * @author [maple]
	 */
	@Override
	public ExecuteResult<User> queryUserInfoById(String userId) {
		ExecuteResult<User> result = new ExecuteResult<User>();
		try {
			if(!"".equals(userId) && userId != null) {
				User queryResult = userMapper.queryUserInfoById(userId);
				if(queryResult == null) {
					result.setResultMessage("查询的用户信息不存在！");
					return result;
				}
				result.setResult(queryResult);
				return result;
				}
			result.addErrorMessage("查询失败！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}


	/**
	 * 
	 * Description:[用户重置密码]
	 * @param User user
	 * @return ExecuteResult<String>
	 * @author [maple]
	 * 2018年4月16日下午10:44:45
	 */
	@Override
	public ExecuteResult<String> resetUserPasswordByUserId(User user) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (StringUtils.isEmpty(user.getPassword())) {
				String random = UUIDUtil.getUUID();
				String generatePassword = random.substring(0, 6);
				String encryptPassword = new Sha256Hash(generatePassword).toHex();
				user.setPassword(encryptPassword);
				int updateResult = userMapper.resetUserPasswordByUserId(user);
				if(updateResult < 0 ) {
					result.addErrorMessage("重置密码失败！");
					return result;
				}
				result.setResult(generatePassword);
				return result;
			}
			String inputPassword = user.getPassword();
			String encryptPassword = new Sha256Hash(inputPassword).toHex();
			user.setPassword(encryptPassword);
			int updateResult = userMapper.resetUserPasswordByUserId(user);
			if(updateResult < 0 ) {
				result.addErrorMessage("重置密码失败！");
				return result;
			}
			result.setResult("重置密码成功！");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

}
