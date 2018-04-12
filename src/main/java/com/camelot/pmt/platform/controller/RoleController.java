package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.platform.util.Tree;
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
 * 角色控制层
 */
@RestController
@RequestMapping(value = "/platform/role")
@Api(value = "基础平台-角色管理接口", description = "角色管理接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "验证角色名称是否存在", notes = "验证角色名称是否存在")
    @GetMapping(value = "/checkRoleName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "query", dataType = "string"),})

    public JSONObject checkRoleName(@ApiIgnore Role role) {
        ExecuteResult result;
        try {
            if (StringUtils.isEmpty(role.getRoleName())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.getRoleNameVerification(role);
            if (result.getResult() == null) {
                return ApiResponse.jsonData(APIStatus.OK_205);
            }
            return ApiResponse.jsonData(APIStatus.OK_206);
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询角色集合
     *
     * @return
     */
    // @RequiresPermissions(value = "/platform/role/queryRoleArray")
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @GetMapping(value = "/queryRoleArray")
    public JSONObject queryRoleArray() {
        ExecuteResult<List<Tree<Role>>> result;
        try {
            result = roleService.queryRoleArray();
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    // @RequiresPermissions(value = "/platform/role/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping(value = "addRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "角色父id", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "角色状态", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "form", dataType = "string"),})
    public JSONObject addRole(@ApiIgnore Role role) {
        ExecuteResult<Role> result;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            role.setCreateUserId(user.getUserId());
            role.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(role.getRoleName())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.addRole(role);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @PostMapping(value = "/editRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "角色状态", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "form", dataType = "string"),})
    public JSONObject editRole(@ApiIgnore Role role) {
        ExecuteResult<Role> result;
        try {

            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            role.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(role.getRoleName()) && StringUtils.isEmpty(role.getRoleId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.editRole(role);
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200);
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping(value = "/deleteRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色32位id", required = true, paramType = "form", dataType = "string"),})
    public JSONObject deleteRole(@ApiIgnore Role role) {
        ExecuteResult<Role> result;
        try {
            if (StringUtils.isEmpty(role.getRoleId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.deleteRole(role);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }
}
