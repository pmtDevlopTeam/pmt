package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.service.RoleToMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @Autowired
    private RoleToMenuService roleToMenuService;

    @ApiOperation(value = "角色绑定权限", notes = "角色绑定权限")
    @PostMapping(value = "/addRoleToMenu")
    public JSONObject addRoleToMenu(@ApiIgnore RoleToMenu roleToMenu){
        ExecuteResult<RoleToMenu> result;
        try {
            //-----------等获取登录用户ID------
            roleToMenu.setCreateUserId("ligen12138");
            if(StringUtils.isEmpty(roleToMenu.getCreateUserId())) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //----------------------------------
            if(StringUtils.isEmpty(roleToMenu.getRoleId()) && StringUtils.isEmpty(roleToMenu.getMenuId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToMenuService.addRoleToMenu(roleToMenu);
            if(result.getResult() == null){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
        } catch (Exception e){
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 修改角色权限菜单
     * @param roleToMenu
     * @return
     */
    @PostMapping(value = "/updateRoleToMenu")
    @ApiOperation(value = "修改角色绑定权限", notes = "修改角色绑定权限")
    public JSONObject updateRoleToMenu(@ApiIgnore RoleToMenu roleToMenu){
        ExecuteResult result;
        try {
            //-----------等获取登录用户ID------
            roleToMenu.setCreateUserId("ligen12138");
            if(StringUtils.isEmpty(roleToMenu.getCreateUserId())) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //----------------------------------
            if(StringUtils.isEmpty(roleToMenu.getRoleId()) && StringUtils.isEmpty(roleToMenu.getMenuIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }

            result = roleToMenuService.updateRoleToMenu(roleToMenu);
            if(result.getResult() == null) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            return ApiResponse.success(result.getResult());
        } catch (Exception e){
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @ApiOperation(value = "根据角色id查询权限菜单", notes = "根据角色id查询权限菜单")
    @PostMapping(value = "/selectMenuByRoleId")
    public JSONObject selectMenuByRoleId(@ApiIgnore RoleToMenu roleToMenu){
        ExecuteResult<List<Menu>> result;
        try {
            if(StringUtils.isEmpty(roleToMenu.getRoleId())){
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleToMenuService.selectMenuByRoleId(roleToMenu);
            return ApiResponse.success(result.getResult());
        } catch (Exception e){
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }
}
