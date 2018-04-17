package com.camelot.pmt.platform.controller;


import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.model.vo.UserVo;
import com.camelot.pmt.platform.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        ExecuteResult<User> result = new ExecuteResult<User>();
        try {
            result = service.findUserByUserId(userId);
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * <p>Description:[查询所有用户]</p>
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel列表}]
     */
    @ApiOperation(value="查询所有用户", notes="查询所有用户")
    @RequestMapping(value = "user/queryAllUsers",method = RequestMethod.GET)
    public JSONObject queryUserAll(){
        ExecuteResult<List<User>> result = new ExecuteResult<List<User>>();
        try {
            result = service.queryAllUsers();
            if(result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        }catch (Exception e) {
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
    				name="roleIds",value="用户角色ID集合，格式1,2,3,4",required=false,paramType="form",dataType="String"),
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
    public JSONObject createUser(@ApiIgnore User userModel) {
    	ExecuteResult<String> result = new ExecuteResult<String>();
		try {
    		//判断非空
	    	if(userModel == null){
	    		return ApiResponse.errorPara();
	    	}
	    	//不为空调用接口查询
	    	 result = service.createUser(userModel);
	    	//成功返回
	    	return ApiResponse.success(result.getResult());
    	} catch (Exception e) {
    		//异常
    		return ApiResponse.error();
		}
	}
    
    /**
     * <p>Description:[更新用户详情页面]</p>
     * @param  User userModel
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {更新用户成功!}]
     */
    @ApiOperation(value="更新用户", notes="更新用户")
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
    	ExecuteResult<String> result = new ExecuteResult<String>();
		try {
    		//判断非空
	    	if(userModel == null){
	    		return ApiResponse.errorPara();
	    	}
	    	//不为空调用接口查询
	    	result = service.modifyUserDetailsByUserId(userModel);
	    	if(result.isSuccess()) {
	    		return ApiResponse.success(result.getResult());
	        }
	    	//更新失败返回
	    	return ApiResponse.error(result.getErrorMessage());
    	} catch (Exception e) {
    		//异常
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
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name="userId",value="用户userId",required=true,paramType="query",dataType="String")
    })
    @RequestMapping(value = "user/deleteUserByUserId",method = RequestMethod.POST)
    public JSONObject deleteUserByUserId(@ApiIgnore User userModel){
    	ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		if("".equals(userModel.getUserId()) || "0".equals(userModel.getUserId()) || userModel.getUserId() == null) {
    			return ApiResponse.jsonData(APIStatus.ERROR_400);
    		}
    		result = service.deleteUserByUserId(userModel);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	} catch (Exception e) {
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
    	ExecuteResult<User> result = new ExecuteResult<User>();
		try {
    		//判断非空
	    	if(userModel == null){
	    		return ApiResponse.errorPara();
	    	}
	    	//不为空调用接口查询
	    	 result = service.queryLoginCodeAndPassword(userModel);
	    	 if(result.getResult() == null) {
	    		 return ApiResponse.success(result.getResultMessage());
	    	 }
	    	//成功返回
	    	return ApiResponse.success(result.getResult());
    	} catch (Exception e) {
    		//异常
    		return ApiResponse.error();
		}
	}
//    
//    /**
//     * <p>Description:[分页查询用户列表]</p>
//     * @param  page 页码,rows 每页数量
//     * @return "data": {"total": 总数量,"rows":[查询的结果集],"status": {"code": 200,"message": "请求处理成功."}}
//     */
//    @ApiOperation(value="分页获取用户列表", notes="分页获取用户列表")
//    @RequestMapping(value = "user/queryUsersByPage",method = RequestMethod.GET)
//    @ApiImplicitParams({
//    	@ApiImplicitParam(name = "page", value = "页码", required = false, paramType = "query", dataType = "int"),
//    	@ApiImplicitParam(name = "rows", value = "每页数量", required = false, paramType = "query", dataType = "int")
//    })
//    public JSONObject queryUsersByPage(@ApiIgnore Pager page){
//    	ExecuteResult<DataGrid<User>> result = new ExecuteResult<DataGrid<User>>();
//    	try {
//    		if(page == null) {
//    			return ApiResponse.errorPara();
//    		}
//    		result = service.queryUsersByPage(page);
//    		if(result.isSuccess()) {
//    			return ApiResponse.success(result.getResult());
//    		}
//    		return ApiResponse.error();
//    	}catch (Exception e) {
//    		return ApiResponse.error();
//    	}
//    }
    
    /**
     * <p>Description:[查询所有用户 和 条件查询]</p>
     * @param  
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userVo列表}]
     */
    @ApiOperation(value="用户列表展示", notes="用户列表展示")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userJobNum", value = "员工号", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "userName", value = "用户名", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "roleId", value = "用户角色ID", required = false, paramType = "query", dataType = "String"),
    	@ApiImplicitParam(name = "state", value = "用户状态", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "user/queryUsersList",method = RequestMethod.GET)
    public JSONObject queryUsersList(@ApiIgnore UserVo userVo){
    	ExecuteResult<List<UserVo>> result = new ExecuteResult<List<UserVo>>();
        try {
            result = service.queryUsersList(userVo);
            return ApiResponse.success(result.getResult());
        }catch (Exception e) {
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
        ExecuteResult<User> result = new ExecuteResult<User>();
        try {
            result = service.queryUserInfoById(userId);
            if(result.getResult() == null) {
                return ApiResponse.success(result.getResultMessage());
            }
            return ApiResponse.success(result.getResult());
        }catch (Exception e) {
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
    	ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		if(StringUtils.isEmpty(userModel.getUserId())) {
    			return ApiResponse.jsonData(APIStatus.ERROR_400);
    		}
    		result = service.resetUserPasswordByUserId(userModel);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	} catch (Exception e) {
    		return ApiResponse.error();
		}
    }


}
