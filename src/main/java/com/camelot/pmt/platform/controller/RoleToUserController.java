package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.RoleToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleToUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 角色操作用户控制层
 */
@Api(value = "基础平台-角色用户管理接口", description = "角色用户管理接口")
@RestController
@RequestMapping(value = "/platform/roleToUser")
public class RoleToUserController {

    @Autowired
    private RoleToUserService roleToUserService;

    @ApiOperation(value = "根据角色绑定用户", notes = "根据角色绑定用户")
    @PostMapping(value = "/addUserByRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id（格式：1,2 其中要有子ID和父ID）", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户id（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string"), })
    public JSONObject addUserByRole(@ApiIgnore RoleToUser roleToUser) {
        ExecuteResult result;
        try {

            //等获取登录人ID
            roleToUser.setCreateUserId("ligen12138");
            roleToUser.setModifyUserId("ligen12138");
            if(StringUtils.isEmpty(roleToUser.getCreateUserId()) && StringUtils.isEmpty(roleToUser.getModifyUserId())){
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //

            if (StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.addUserByRole(roleToUser);
            if (result.getResult() == null) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 根据角色修改用户
     * 
     * @param roleToUser
     * @return
     */
    @ApiOperation(value = "根据角色修改用户", notes = "根据角色修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id（格式：1,2 其中要有子ID和父ID）", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户id（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string"), })
    @PostMapping(value = "/updateUserByRole")
    public JSONObject updateUserByRole(@ApiIgnore RoleToUser roleToUser) {
        ExecuteResult result;
        try {

            //等获取登录人ID
            roleToUser.setModifyUserId("ligen12138");
            if(StringUtils.isEmpty(roleToUser.getModifyUserId())){
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //


            if (StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.updateUserByRole(roleToUser);
            if (result.getResult() == null) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 根据角色id查询用户列表
     * 
     * @return
     */
    @GetMapping(value = "queryUserByRole")
    @ApiOperation(value = "根据角色id查询用户列表", notes = "根据角色id查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "string"),})
    public JSONObject queryUserByRole(@ApiIgnore RoleToUser role) {
        ExecuteResult<List<User>> result;
        try {
            if (StringUtils.isEmpty(role.getRoleId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.queryUserByRole(role);
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }

    }

}
