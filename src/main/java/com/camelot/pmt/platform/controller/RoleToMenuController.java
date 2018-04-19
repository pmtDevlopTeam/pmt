package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.RoleToMenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 角色绑定权限控制层
 */
@RestController
@RequestMapping(value = "/platform/roleToMenu")
@Api(value = "角色绑定权限管理", description = "角色绑定权限管理")
public class RoleToMenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleToMenuService roleToMenuService;

    /**
     * 角色绑定权限
     *
     * @param String
     *            roleId, String menuId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "角色绑定权限", notes = "角色绑定权限")
    @PostMapping(value = "/createRoleToMenu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "menuIds", value = "菜单ids（格式：1,2,3,4）要有子id和父id", required = true, paramType = "form", dataType = "string"), })
    public JSONObject createRoleToMenu(@ApiIgnore RoleToMenu roleToMenu) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            roleToMenu.setCreateUserId(user.getUserId());
            roleToMenu.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(roleToMenu.getRoleId()) && StringUtils.isEmpty(roleToMenu.getMenuId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            flag = roleToMenuService.createRoleToMenu(roleToMenu);
            if (flag) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.jsonData(APIStatus.OK_200);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 修改角色绑定权限
     *
     * @param String
     *            roleId, String menuIds
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateRoleToMenu")
    @ApiOperation(value = "修改角色绑定权限", notes = "修改角色绑定权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "menuIds", value = "菜单ids（格式：1,2,3,4 要有子id和父id）", required = true, paramType = "form", dataType = "string"), })
    public JSONObject updateRoleToMenu(@ApiIgnore RoleToMenu roleToMenu) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            roleToMenu.setCreateUserId(user.getUserId());
            roleToMenu.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(roleToMenu.getRoleId()) && StringUtils.isEmpty(roleToMenu.getMenuIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }

            flag = roleToMenuService.updateRoleToMenu(roleToMenu);
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
     * 根据角色id查询权限菜单
     *
     * @param String
     *            roleId
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据角色id查询权限菜单", notes = "根据角色id查询权限菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "string"), })

    @GetMapping(value = "/selectMenuByRoleId")
    public JSONObject selectMenuByRoleId(@ApiIgnore RoleToMenu roleToMenu) {
        try {
            if (StringUtils.isEmpty(roleToMenu.getRoleId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            List<Menu> list = roleToMenuService.selectMenuByRoleId(roleToMenu);
            return ApiResponse.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }
}
