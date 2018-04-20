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
import com.camelot.pmt.platform.model.Role;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.Tree;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色控制层
 */
@RestController
@RequestMapping(value = "/platform/role")
@Api(value = "基础平台-角色管理接口", description = "角色管理接口")
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     *
     * @param String
     *            parentId, String state, String roleName
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    // @RequiresPermissions(value = "/platform/role/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping(value = "/addRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "角色父id", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "角色状态", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "form", dataType = "string"), })
    public JSONObject addRole(@ApiIgnore Role role) {
        boolean flag = false;
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
            flag = roleService.addRole(role);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 删除角色
     *
     * @param Role
     *            role
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping(value = "/deleteRoleById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色32位id", required = true, paramType = "form", dataType = "string"), })
    public JSONObject deleteRoleById(@ApiIgnore Role role) {
        boolean flag = false;
        try {
            if (StringUtils.isEmpty(role.getRoleId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleService.deleteRoleById(role);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 编辑角色
     *
     * @param String
     *            roleId, String state, String roleName
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @PostMapping(value = "/updateRoleById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "角色状态", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "form", dataType = "string"), })
    public JSONObject updateRoleById(@ApiIgnore Role role) {
        boolean flag = false;
        try {

            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            role.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(role.getRoleName()) && StringUtils.isEmpty(role.getRoleId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleService.updateRoleById(role);
            if (flag) {
                return ApiResponse.jsonData(APIStatus.OK_200);
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询角色列表
     *
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    // @RequiresPermissions(value = "/platform/role/queryRoleArray")
    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @GetMapping(value = "/queryAllRoleList")
    public JSONObject queryAllRoleList() {
        try {
            List<Tree<Role>> list = roleService.queryAllRole();
            return ApiResponse.jsonData(APIStatus.OK_200, list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 验证角色名称是否存在
     *
     * @param String
     *            roleName
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "验证角色名称是否存在", notes = "验证角色名称是否存在")
    @GetMapping(value = "/checkRoleName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, paramType = "query", dataType = "string"), })
    public JSONObject checkRoleName(@ApiIgnore Role role) {
        boolean flag = false;
        try {
            if (StringUtils.isEmpty(role.getRoleName())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleService.getRoleNameVerification(role);
            if (flag) {
                return ApiResponse.jsonData(APIStatus.OK_205);
            }
            return ApiResponse.jsonData(APIStatus.OK_206);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

}
