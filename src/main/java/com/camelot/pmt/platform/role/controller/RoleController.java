package com.camelot.pmt.platform.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.role.model.Role;
import com.camelot.pmt.platform.role.service.IRoleService;
import com.camelot.pmt.platform.utils.ExecuteResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询角色集合
     * @return
     */
//    @RequiresPermissions(value = "/platform/role/queryRoleArray")
    @GetMapping(value = "/queryRoleArray")
    public JSONObject queryRoleArray(){
        ExecuteResult<List<Role>> result;
        try {
            result = roleService.queryRoleArray();
            if(result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }

    //@RequiresPermissions(value = "/platform/role/addRole")
    @PostMapping(value = "addRole")
    public JSONObject addRole(@ApiIgnore Role role){
        ExecuteResult<Role> result;
        try{
            if (StringUtils.isEmpty(role.getRoleName())){
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.addRole(role);
            if (result.isSuccess()){
              return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }

    @PostMapping(value = "/editRole")
    public JSONObject editRole(@ApiIgnore Role role){
        ExecuteResult<Role> result;
        try {
            if(StringUtils.isEmpty(role.getRoleName()) && StringUtils.isEmpty(role.getRoleId())){
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.editRole(role);
            if(result.isSuccess()){
                return ApiResponse.jsonData(APIStatus.OK_200);
            }
           return ApiResponse.error();
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }

    @PostMapping(value = "/deleteRole")
    public JSONObject deleteRole(@ApiIgnore Role role){
        ExecuteResult<Role> result;
        try {
            if(StringUtils.isEmpty(role.getRoleId())){
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
            result = roleService.deleteRole(role);
            if(result.isSuccess()){
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e){
            return ApiResponse.errorPara(e);
        }
    }
}
