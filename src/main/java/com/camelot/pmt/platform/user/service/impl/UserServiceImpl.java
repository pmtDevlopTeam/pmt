package com.camelot.pmt.platform.user.service.impl;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.camelot.pmt.platform.user.mapper.UserMapper;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.user.service.UserService;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @Title: UserServiceImpl.java
 * @Description: TODO(用户操作实现层)
 * @author: jh
 * @date: 2018年2月5日 下午3:12:12
 */
@Service
@Primary
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * <p>
     * Description:[新增用户]
     * <p>
     * 调用mapper方法:insertUser
     * 
     * @param userModel
     * @return ExecuteResult<UserModel>
     */
    @Override
    public ExecuteResult<String> save(UserModel userModel) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (userModel == null) {
                result.addErrorMessage("传入的用户实体有误!");
                return result;
            }
            // 对象不为空则添加新的项目实体
            String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            userModel.setUserId(userId);
            String inputPassword = userModel.getPassword();
            String encryptPassword = new Sha256Hash(inputPassword).toHex();
            userModel.setPassword(encryptPassword);
            userMapper.insertUser(userModel);
            result.setResult("添加用户成功!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[删除用户]
     * <p>
     * 
     * @param userModel
     * @return ExecuteResult<String>
     */
    @Override
    public ExecuteResult<String> delete(UserModel userModel) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            userMapper.deleteUserById(userModel);
            result.setResult("删除用户成功！");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[查询所有用户不分页]
     * <p>
     * 
     * @return ExecuteResult<List<UserModel>>
     */
    @Override
    public ExecuteResult<List<UserModel>> findAllUsers() {
        ExecuteResult<List<UserModel>> result = new ExecuteResult<List<UserModel>>();
        try {
            List<UserModel> list = userMapper.selectUsersAll();
            if (list.size() <= 0) {
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
     * <p>
     * Description:[根据userId获取单个用户信息]
     * <p>
     * 
     * @return ExecuteResult<UserModel>
     */
    @Override
    public ExecuteResult<UserModel> findUserById(String userId) {
        ExecuteResult<UserModel> result = new ExecuteResult<UserModel>();
        try {
            if (!userId.equals("") && !userId.equals("0")) {
                UserModel queryResult = userMapper.selectUserById(userId);
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
     * <p>
     * Description:[根据用户登录账号和密码检查用户信息]
     * <p>
     * 
     * @return ExecuteResult<UserModel>
     */
    @Override
    public ExecuteResult<UserModel> queryLoginCodeAndPassword(UserModel userModel) {
        // 返回
        ExecuteResult<UserModel> result = new ExecuteResult<UserModel>();
        try {
            if (userModel != null && !userModel.getLoginCode().equals("") && userModel.getLoginCode() != null) {
                // 1.获取用户输入的登录账号
                String inputLoginCode = userModel.getLoginCode();
                // 2.根据登录账号去库中获取用户信息,检查用户是否存在
                UserModel dbModel = userMapper.findUserByLoginCode(inputLoginCode);
                if (dbModel == null) {
                    result.setResultMessage("该用户不存在！");
                    return result;
                }
                // 3.记录存在，再检查用的输入密码与库里的密码是否匹配
                String dbPassword = dbModel.getPassword();
                String inputPassword = userModel.getPassword();
                String encryptPassword = new Sha256Hash(inputPassword).toHex();
                if (!dbPassword.equals(encryptPassword)) {
                    result.setResultMessage("密码不正确!");
                    return result;
                }
                userModel.setPassword(dbPassword);
                UserModel reusltUserModel = userMapper.checkUserLoginCodeAndPassword(userModel);
                result.setResult(reusltUserModel);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[分页查询用户列表]
     * <p>
     * 
     * @param page
     * @return ExecuteResult<UserModel>
     */
    @Override
    public ExecuteResult<DataGrid<UserModel>> queryUsers(Pager page) {
        ExecuteResult<DataGrid<UserModel>> result = new ExecuteResult<DataGrid<UserModel>>();
        try {
            List<UserModel> list = userMapper.findUsersByPage(page);
            // 如果没有查询到数据，不继续进行
            if (CollectionUtils.isEmpty(list)) {
                DataGrid<UserModel> dg = new DataGrid<UserModel>();
                result.setResult(dg);
                return result;
            }
            DataGrid<UserModel> dg = new DataGrid<UserModel>();
            dg.setRows(list);
            // 查询总条数
            Long total = userMapper.queryCount();
            dg.setTotal(total);
            result.setResult(dg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * <p>
     * Description:[更新用户]
     * <p>
     * 调用mapper方法:updateUserById
     * 
     * @param userModel
     * @return ExecuteResult<String>
     */
    // @Override
    // public ExecuteResult<String> editUserById(UserModel userModel) {
    // ExecuteResult<String> result = new ExecuteResult<String>();
    // try{
    // if(userModel.getId() == 0 || userModel.getId() == null){
    // result.setResult("该用户不存在!");
    // return result;
    // }
    // //不更新密码的情况下编辑
    // if(userModel.getPassword() == null || userModel.getPassword().equals("") ||
    // userModel.getNewPassword() == null || userModel.getNewPassword().equals("")){
    // Integer count = userMapper.updateUserById(userModel);
    // if(count == 0){
    // result.setResult("更新用户失败!");
    // return result;
    // }
    // }
    // //更新密码的情况下编辑
    // if(userModel.getPassword() != null && !userModel.getPassword().equals("") &&
    // userModel.getNewPassword() != null &&
    // !userModel.getNewPassword().equals("")){
    // String password = userModel.getPassword();
    // String oldPassword = userMapper.findUserPasswordById(userModel);
    // if(password.equals(oldPassword)){
    // userModel.setPwdStatus((byte)2);
    // Integer count = userMapper.updateUserById(userModel);
    // if(count == 0){
    // result.setResult("更新用户失败!");
    // return result;
    // }
    // }else if(!password.equals(oldPassword)){
    // result.setResult("旧密码输入不正确!");
    // return result;
    // }
    // }
    // }catch(Exception e){
    // LOGGER.error(e.getMessage());
    // throw new RuntimeException(e);
    // }
    // result.setResult("更新用户成功!");
    // return result;
    // }

    //
    //
}
