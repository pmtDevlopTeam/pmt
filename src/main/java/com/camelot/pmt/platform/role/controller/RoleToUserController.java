package com.camelot.pmt.platform.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.role.model.RoleToUser;
import com.camelot.pmt.platform.role.service.IRoleToUserService;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.ExecuteResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping(value = "/platform/roleToUser")
@Api(value="基础平台-角色用户管理接口", consumes="基础平台-角色用户管理接口:提供角色可操控用户是否拥有权限接口")
public class RoleToUserController {

    @Autowired
    private IRoleToUserService roleToUserService;

    @ApiOperation(value = "根据角色绑定用户", notes = "根据角色绑定用户")
    @PostMapping(value = "/addUserByRole")
    public JSONObject addUserByRole(@ApiIgnore RoleToUser roleToUser){
        ExecuteResult result;
        try {
            if(StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.addUserByRole(roleToUser);
            if(result.getResult() == null){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            if(result.isSuccess()){
                return ApiResponse.success();
            }
            return  ApiResponse.error();
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }

    /**
     * 根据角色修改用户
     * @param roleToUser
     * @return
     */
    @ApiOperation(value = "根据角色修改用户", notes = "根据角色修改用户")
    @PostMapping(value = "/updateUserByRole")
    public JSONObject updateUserByRole(@ApiIgnore RoleToUser roleToUser){
        ExecuteResult result;
        try {
            if(StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.updateUserByRole(roleToUser);
            if(result.getResult() == null){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.success(result.getResult());
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }

    /**
     * 根据角色id查询用户列表
     * @return
     */
    @GetMapping(value = "queryUserByRole")
    @ApiOperation(value = "根据角色id查询用户列表", notes = "根据角色id查询用户列表")
    public JSONObject queryUserByRole(@ApiIgnore RoleToUser role){
        ExecuteResult<List<UserModel>> result;
        try {
            if(StringUtils.isEmpty(role.getRoleId())){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToUserService.queryUserByRole(role);
            return ApiResponse.success(result.getResult());
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }

    }

}
