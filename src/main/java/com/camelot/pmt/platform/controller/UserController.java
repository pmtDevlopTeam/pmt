package com.camelot.pmt.platform.controller;


import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.camelot.pmt.platform.service.UserService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

/**
 * 用户管理模块
 * @author maple
 */
@RestController
@Api(value = "用户管理接口", description = "用户管理接口")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    /**
     * <p>Description:[查询单个用户]</p>
     * @param  String userId
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
     */
    @ApiOperation(value="根据userId查询单个用户", notes="查询单个用户")
    @RequestMapping(value = "user/queryUserById",method = RequestMethod.POST)
    public JSONObject queryUserByUserId(@ApiParam(name="userId",value = "用户useId", required = true) @RequestParam(required = true) String userId){
        try {
        	if(StringUtils.isEmpty(userId)) {
        		return ApiResponse.jsonData(APIStatus.ERROR_400);
        	}
            User result = service.queryUserByUserId(userId);
            if(result != null) {
                return ApiResponse.success(result);
            }else {
            	return ApiResponse.success("该用户不存在！");
            }
        }catch (Exception e) {
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    /**
     * <p>Description:[查询所有用户]</p>
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel列表}]
     */
    @ApiOperation(value="查询所有用户", notes="查询所有用户")
    @RequestMapping(value = "user/queryAllUsers",method = RequestMethod.POST)
	public JSONObject queryUserAll() {
		try {
			List<User> allUsers = service.queryAllUsers();
			return ApiResponse.success(allUsers);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.error();
		}
    }

    /**
     * <p>Description:[添加用户]</p>
     * @param  User userModel
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {添加用户成功!}]
     */
    @ApiOperation(value="添加用户", notes="添加用户")
    @ApiImplicitParams({
    		@ApiImplicitParam(
    				name="userJobNum",value="用户工号",required=true,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="username",value="用户名称",required=true,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="orgId",value="用户所属部门ID",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="roleIds",value="用户角色IDS",required=false,paramType="form",dataType="String"),
        	@ApiImplicitParam(
        			name="loginCode",value="登录账号",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="password",value="密码",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="userPhone",value="用户电话",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="userMail",value="用户邮箱",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="createUserId",value="用户创建人ID",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="modifyUserId",value="用户修改人ID",required=false,paramType="form",dataType="String")
    })
    @RequestMapping(value = "user/addUser",method = RequestMethod.POST)
	public JSONObject addUser(@ApiIgnore User userModel) {
		try {
			if (StringUtils.isEmpty(userModel.getUserJobNum())) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
			}
			String result = service.addUser(userModel);
			return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.error();
		}
	}
    
    /**
     * <p>Description:[更新用户详情页面]</p>
     * @param  User userModel
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {更新用户成功!}]
     */
    @ApiOperation(value="更新用户详情", notes="更新用户详情")
    @ApiImplicitParams({
    		@ApiImplicitParam(
    				name="userId",value="用户ID",required=true,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="username",value="用户名称",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="loginCode",value="登录账号",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="password",value="密码",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="state",value="用户状态",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="userPhone",value="用户电话",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="userMail",value="用户邮箱",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="orgId",value="用户所属部门ID",required=false,paramType="form",dataType="String"),
    		@ApiImplicitParam(
    				name="roleIds",value="用户角色ID",required=false,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="modifyUserId",value="用户修改人ID",required=false,paramType="form",dataType="String")
    })
    @RequestMapping(value = "user/modifyUserDetailsByUserId",method = RequestMethod.POST)
    public JSONObject modifyUserDetailsByUserId(@ApiIgnore User userModel) {
		try {
			if(StringUtils.isEmpty(userModel.getUserId())){
	    		return ApiResponse.jsonData(APIStatus.ERROR_400);
	    	}
	    	String result = service.modifyUserDetailsByUserId(userModel);
	    	return ApiResponse.success(result);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.error();
		}
	}

    /**
     * 
     *<p>Description:[删除用户]</p>
     * @param User userModel
     * @return JSONObject
     * @author [maple]
     */
    @ApiOperation(value="删除用户", notes="删除用户")
	@RequestMapping(value = "user/deleteUserByUserId", method = RequestMethod.POST)
	public JSONObject deleteUserByUserId(
			@ApiParam(name = "userId", value = "用户useId", required = true) @RequestParam(required = true) String userId) {
		try {
			if (StringUtils.isEmpty(userId)) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
			}
			boolean deleteResult = service.deleteUserByUserId(userId);
			if (deleteResult) {
                return ApiResponse.success();
            }else {
            	return ApiResponse.error();
            }
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.error();
		}
	}
    
    
    /**
     * <p>Description:[检查用户账号密码]</p>
     * @param  loginCode 登录账号,password 密码
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
     */
    @ApiOperation(value="检查用户账号密码", notes="检查用户账号密码")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name="loginCode",value="登录账号",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="password",value="密码",required=true,paramType="form",dataType="String")
    })
    @RequestMapping(value = "user/checkUser",method = RequestMethod.POST)
	public JSONObject checkUser(@ApiIgnore User userModel) {
		try {
			if (StringUtils.isEmpty(userModel.getLoginCode()) || StringUtils.isEmpty(userModel.getPassword())) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
			}
			User result = service.queryLoginCodeAndPassword(userModel);
			return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.error();
		}
	}

    
    /**
     * <p>Description:[查询所有用户 和 条件查询]</p>
     * @param  
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userVo列表}]
     */
    @ApiOperation(value="用户详情列表展示", notes="用户详情列表展示")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userJobNum", value = "员工号", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "userName", value = "用户名", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "roleId", value = "用户角色ID", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "state", value = "用户状态", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1" ,required = true, paramType = "query", dataType = "int"),
    	@ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10" ,required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "user/queryUsersList",method = RequestMethod.POST)
    public JSONObject queryUsersList(@ApiIgnore UserVo userVo,@RequestParam int pageNum,@RequestParam int pageSize){
        try {
            PageInfo result = service.queryUsersList(userVo, pageNum, pageSize);
            return ApiResponse.success(result);
        }catch (Exception e) {
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }
    
    /**
     * 
     * Description:[]
     * @param String userId
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {user}]
     * @author [maple]
     * 2018年4月13日下午3:57:32
     */
    @ApiOperation(value="根据userId查询单个用户的信息", notes="查询单个用户信息表")
    @RequestMapping(value = "user/queryUserInfoById",method = RequestMethod.POST)
    public JSONObject queryUserInfoById(@ApiParam(name="userId",value = "用户useId", required = true) @RequestParam(required = true) String userId){
        try {
        	if(StringUtils.isEmpty(userId)) {
        		return ApiResponse.jsonData(APIStatus.ERROR_400);
        	}
            User result = service.queryUserInfoById(userId);
            if(result != null) {
                return ApiResponse.success(result);
            }else {
            	return ApiResponse.success("此用户不存在！");
            }
        }catch (Exception e) {
        	logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }
    
    @ApiOperation(value="根据用户userId重置用户密码", notes="重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name="userId",value="用户userId",required=true,paramType="form",dataType="String"),
            @ApiImplicitParam(
                    name="password",value="用户password",required=false,paramType="form",dataType="String")
    })
    @RequestMapping(value = "user/resetPasswordByUserId",method = RequestMethod.POST)
    public JSONObject resetUserPasswordByUserId(@ApiIgnore User userModel){
    	try {
    		if(StringUtils.isEmpty(userModel.getUserId())) {
    			return ApiResponse.jsonData(APIStatus.ERROR_400);
    		}
    		String result = service.resetUserPasswordByUserId(userModel);
    		return ApiResponse.success(result);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.error();
		}
    }
    
    @ApiOperation(value="根据用户名称模糊查询用户列表", notes="根据用户名模糊查询获取用户列表")
    @RequestMapping(value = "user/queryUsersByUserName",method = RequestMethod.POST)
    public JSONObject queryUsersByUserName(@ApiParam(name="username",value = "用户名称", required = true) @RequestParam(required = true) String username){
         try {
        	 if(StringUtils.isEmpty(username)) {
     			return ApiResponse.jsonData(APIStatus.ERROR_400);
     		}
             List<User> result = service.queryUsersByUserName(username);
             return ApiResponse.success(result);
         }catch (Exception e) {
        	 logger.error(e.getMessage());
             return ApiResponse.error();
         }
    }


}
