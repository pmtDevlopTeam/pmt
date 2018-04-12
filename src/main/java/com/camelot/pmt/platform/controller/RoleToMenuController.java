package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.RoleToMenu;
import com.camelot.pmt.platform.service.RoleToMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色绑定权限控制层
 */
@RestController
@RequestMapping(value = "/platform/roleToMenu")
public class RoleToMenuController {

    @Autowired
    private RoleToMenuService roleToMenuService;

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
}
