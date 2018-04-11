package com.camelot.pmt.platform.service.impl;


import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.mapper.UserMapper;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.UserService;

import java.util.List;
import java.util.UUID;

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
     * 调用mapper方法:insertUser
     * @param User
     * @return ExecuteResult<User>
     */
    @Override
    public ExecuteResult<String> save(User user) {
    	ExecuteResult<String> result = new ExecuteResult<String>();
		try{
			if(user == null){
				result.addErrorMessage("传入的用户实体有误!");
				return result;
			}
			//1.插入用户表
			String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			user.setUserId(userId);
			String inputPassword = user.getPassword();
			String encryptPassword = new Sha256Hash(inputPassword).toHex();
			user.setPassword(encryptPassword);
			userMapper.insertUser(user);
			//2.插入用户信息表
			userMapper.insertUserInfo(user);
			//3.插入用户组织表
			userMapper.insertUserOrg(user);
			//4.插入用户角色表
			userMapper.insertUserRole(user);
			result.setResult("添加用户成功!");
		}catch(Exception e){
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
    }


    /**
     * <p>Description:[删除用户]<p>
     * @param User
     * @return ExecuteResult<String>
     */
	@Override
	public ExecuteResult<String> delete(User user) {
		ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		userMapper.deleteUserById(user);
    		result.setResult("删除用户成功！");
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
			throw new RuntimeException(e);
		}
        return result;
	}


	/**
     * <p>Description:[查询所有用户不分页]<p>
     * @return ExecuteResult<List<User>>
     */
	@Override
	public ExecuteResult<List<User>> findAllUsers() {
    	ExecuteResult<List<User>> result = new ExecuteResult<List<User>>();
    	try {
    		List<User> list = userMapper.selectUsersAll();
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
     * <p>Description:[根据userId获取单个用户信息]<p>
     * @return ExecuteResult<User>
     */
	@Override
	public ExecuteResult<User> findUserById(String userId) {
		ExecuteResult<User> result = new ExecuteResult<User>();
		try {
			if(!userId.equals("") && !userId.equals("0")) {
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
     * <p>Description:[根据用户登录账号和密码检查用户信息]<p>
     * @return ExecuteResult<User>
     */
	@Override
	public ExecuteResult<User> queryLoginCodeAndPassword(User user) {
		//返回
		ExecuteResult<User> result = new ExecuteResult<User>();
		try {
			if(user != null && !user.getLoginCode().equals("") && user.getLoginCode() != null ) {
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


	/**
     * <p>Description:[分页查询用户列表]<p>
     * @param page
     * @return ExecuteResult<User>
     */
	@Override
	public ExecuteResult<DataGrid<User>> queryUsers(Pager page) {
		ExecuteResult<DataGrid<User>> result = new ExecuteResult<DataGrid<User>>();
		try{
            List<User> list = userMapper.findUsersByPage(page);
            //如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
            	DataGrid<User> dg = new DataGrid<User>();
            	result.setResult(dg);
                return result;
            }            
            DataGrid<User> dg = new DataGrid<User>();
            dg.setRows(list);
            //查询总条数
            Long total = userMapper.queryCount();
            dg.setTotal(total);				
            result.setResult(dg);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return result;
	}

    /**
     * <p>Description:[更新用户]<p>
     * 调用mapper方法:updateUserById
     * @param User
     * @return ExecuteResult<String>
     */
//    @Override
//	public ExecuteResult<String> editUserById(User User) {
//    	ExecuteResult<String> result = new ExecuteResult<String>();
//		try{
//			if(User.getId() == 0 || User.getId() == null){
//				result.setResult("该用户不存在!");
//				return result;
//			}
//			//不更新密码的情况下编辑
//			if(User.getPassword() == null || User.getPassword().equals("") || User.getNewPassword() == null || User.getNewPassword().equals("")){
//				Integer count = userMapper.updateUserById(User);
//				if(count == 0){
//					result.setResult("更新用户失败!");
//					return result;
//				}
//			}
//			//更新密码的情况下编辑
//			if(User.getPassword() != null && !User.getPassword().equals("") && User.getNewPassword() != null && !User.getNewPassword().equals("")){
//				String password = User.getPassword();
//				String oldPassword = userMapper.findUserPasswordById(User);
//				if(password.equals(oldPassword)){
//					User.setPwdStatus((byte)2);
//					Integer count = userMapper.updateUserById(User);
//					if(count == 0){
//						result.setResult("更新用户失败!");
//						return result;
//					}
//				}else if(!password.equals(oldPassword)){
//					result.setResult("旧密码输入不正确!");
//					return result;
//				}
//			}
//		}catch(Exception e){
//			LOGGER.error(e.getMessage());
//			throw new RuntimeException(e);
//		}
//		result.setResult("更新用户成功!");
//		return result;
//	}
    
//
//    
}
