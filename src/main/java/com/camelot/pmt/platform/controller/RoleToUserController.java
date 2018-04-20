package com.camelot.pmt.platform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.RoleToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleToUserService;
import com.camelot.pmt.platform.shiro.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色操作用户控制层
 */
@Api(value = "基础平台-角色用户管理接口", description = "角色用户管理接口")
@RestController
@RequestMapping(value = "/platform/roleToUser")
public class RoleToUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleToUserService roleToUserService;

    /**
     * 根据角色绑定用户
     *
     * @param String
     *            roleIds, String userId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据角色绑定用户", notes = "根据角色绑定用户")
    @PostMapping(value = "/addUserByRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id（格式：1,2 其中要有子ID和父ID）", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户id（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string"), })
    public JSONObject addUserByRole(@ApiIgnore RoleToUser roleToUser) {
        boolean flag = false;
        try {

            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            roleToUser.setCreateUserId(user.getUserId());
            roleToUser.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleToUserService.addUserByRole(roleToUser);
            if (flag) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 根据角色修改用户
     *
     * @param String
     *            roleIds, String userIds
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据角色修改用户", notes = "根据角色修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id（格式：1,2 其中要有子ID和父ID）", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户id（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string"), })
    @PostMapping(value = "/updateUserByRole")
    public JSONObject updateUserByRole(@ApiIgnore RoleToUser roleToUser) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            roleToUser.setCreateUserId(user.getUserId());
            roleToUser.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(roleToUser.getRoleIds()) && StringUtils.isEmpty(roleToUser.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleToUserService.updateUserByRole(roleToUser);
            if (flag) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 根据角色id查询用户列表
     *
     * @param String
     *            roleId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @GetMapping(value = "/queryUserByRole")
    @ApiOperation(value = "根据角色id查询用户列表", notes = "根据角色id查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "string"), })
    public JSONObject queryUserByRole(@ApiIgnore RoleToUser role) {
        try {
            if (StringUtils.isEmpty(role.getRoleId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            List<User> list = roleToUserService.queryUserByRole(role);
            return ApiResponse.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }

    }

}
