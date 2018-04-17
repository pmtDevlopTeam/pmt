package com.camelot.pmt.platform.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.user.service.UserService;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 用户管理模块
 * 
 * @author maple
 */
@RestController
@Api(value = "用户管理接口", description = "用户管理接口")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * <p>
     * Description:[查询单个用户]
     * </p>
     * 
     * @param userId
     *            用户UUID
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
     */
    @ApiOperation(value = "根据userId查询单个用户", notes = "查询单个用户")
    @RequestMapping(value = "user/queryUserById", method = RequestMethod.POST)
    public JSONObject queryUserByUserId(
            @ApiParam(name = "userId", value = "用户useId", required = true) @RequestParam(required = true) String userId) {
        ExecuteResult<UserModel> result = new ExecuteResult<UserModel>();
        try {
            result = service.findUserById(userId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * <p>
     * Description:[查询所有用户]
     * </p>
     * 
     * @param username
     *            用户名,password 密码,role 角色,phone 电话,email 邮箱
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel列表}]
     */
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @RequestMapping(value = "user/queryUserAll", method = RequestMethod.GET)
    public JSONObject queryUserAll() {
        ExecuteResult<List<UserModel>> result = new ExecuteResult<List<UserModel>>();
        try {
            result = service.findAllUsers();
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * <p>
     * Description:[添加用户]
     * </p>
     * 
     * @param loginCode
     *            登录账号,password 密码,username 用户名称
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {添加用户成功!}]
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginCode", value = "登录账号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "user/addUser", method = RequestMethod.POST)
    public JSONObject addUser(@ApiIgnore UserModel userModel) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 判断非空
            if (userModel == null) {
                return ApiResponse.errorPara();
            }
            // 不为空调用接口查询
            result = service.save(userModel);
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     * 
     * <p>
     * Description:[删除用户]
     * </p>
     * 
     * @param userModel
     * @return JSONObject
     * @author [maple]
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户userId", required = true, paramType = "query", dataType = "String") })
    @RequestMapping(value = "user/deleteUserByUserId", method = RequestMethod.POST)
    public JSONObject deleteUserByUserId(@ApiIgnore UserModel userModel) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            if (userModel.getUserId().equals("") || userModel.getUserId().equals("0")
                    || userModel.getUserId() == null) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = service.delete(userModel);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * <p>
     * Description:[检查用户账号密码]
     * </p>
     * 
     * @param loginCode
     *            登录账号,password 密码
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
     */
    @ApiOperation(value = "检查用户账号密码", notes = "检查用户账号密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginCode", value = "登录账号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "user/checkUser", method = RequestMethod.POST)
    public JSONObject checkUser(@ApiIgnore UserModel userModel) {
        ExecuteResult<UserModel> result = new ExecuteResult<UserModel>();
        try {
            // 判断非空
            if (userModel == null) {
                return ApiResponse.errorPara();
            }
            // 不为空调用接口查询
            result = service.queryLoginCodeAndPassword(userModel);
            if (result.getResult() == null) {
                return ApiResponse.success(result.getResultMessage());
            }
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     * <p>
     * Description:[分页查询用户列表]
     * </p>
     * 
     * @param page
     *            页码,rows 每页数量
     * @return "data": {"total": 总数量,"rows":[查询的结果集],"status": {"code":
     *         200,"message": "请求处理成功."}}
     */
    @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
    @RequestMapping(value = "user/queryUsersByPage", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int") })
    public JSONObject queryUsersByPage(@ApiIgnore Pager page) {
        ExecuteResult<DataGrid<UserModel>> result = new ExecuteResult<DataGrid<UserModel>>();
        try {
            if (page == null) {
                return ApiResponse.errorPara();
            }
            result = service.queryUsers(page);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    // /**
    // * 修改用户
    // * @param id :用户id,username 用户名,password 原始密码,newPassword:新密码,role
    // 角色,phone
    // 电话,email 邮箱
    // * @return 返回修改用户结果:{"status": "message": "请求处理成功.","code": 200,{"data":
    // "更新用户成功!"}}
    // */
    // @ApiOperation(value="修改用户", notes="修改用户")
    // @ApiImplicitParams({
    // @ApiImplicitParam(
    // name="id",value="用户id",required=true,paramType="form",dataType="Integer"),
    // @ApiImplicitParam(
    // name="username",value="用户名",required=false,paramType="form",dataType="String"),
    // @ApiImplicitParam(
    // name="password",value="原始密码",required=false,paramType="form",dataType="String"),
    // @ApiImplicitParam(
    // name="newPassword",value="新密码",required=false,paramType="form",dataType="String"),
    // @ApiImplicitParam(
    // name="role",value="角色",required=false,paramType="form",dataType="int"),
    // @ApiImplicitParam(
    // name="phone",value="电话",required=false,paramType="form",dataType="String"),
    // @ApiImplicitParam(
    // name="email",value="邮箱",required=false,paramType="form",dataType="String")
    // })
    // @RequiresPermissions(value="user:editUser")
    // @RequestMapping(value = "user/editUser",method = RequestMethod.POST)
    // @ResponseBody
    // public JSONObject editUser(@ApiIgnore UserModel userModel){
    // ExecuteResult<String> result = new ExecuteResult<String>();
    // try{
    // if(userModel.getId() == null && "".equals(userModel.getId()) &&
    // userModel.getId() != 0) {
    // return ApiResponse.errorPara();
    // }
    // //调用接口进行更新
    // result = service.editUserById(userModel);
    // return ApiResponse.success(result.getResult());
    // }catch (Exception e) {
    // //异常
    // return ApiResponse.error();
    // }
    // }
    //

    // /**
    // * 修改用户密码
    // * @param oldPassword 旧密码
    // * @param newPassword 新密码
    // * @return 返回结果集 （success：成功，fail：失败）
    // */
    // @ApiOperation(value="修改用户密码", notes="修改用户密码")
    // @RequiresPermissions(value="user:editPassword")
    // @RequestMapping(value = "user/editPassword",method = RequestMethod.GET)
    // @ResponseBody
    // public JSONObject editPassword(String oldPassword, String newPassword){
    // ExecuteResult<String> result = new ExecuteResult<String>();
    // UserModel user = (UserModel)SecurityUtils.getSubject().getPrincipal();
    // if (user.getPassword().equals(oldPassword)){
    // user.setPassword(newPassword);
    // result = service.editByPassword(user);
    // return ApiResponse.success(result.getResult());
    // }else {
    // return ApiResponse.errorPara("用户密码输入错误");
    // }
    // }

}
