package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequestMapping(value = "/platform/menu")
@Api(value = "基础平台-菜单管理接口", description = "基础平台-菜单管理接口")
public class MenuController {

    @Autowired
    MenuService menuService;
    
    @Autowired
    ShiroUtils shiroUtils;
    
    @Autowired
    ExecuteResult result;
    
    /**
     * 根据一个菜单对象 创建一个菜单
     * 
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "创建菜单接口", notes = "创建单个菜单")
    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public JSONObject createMenu(Menu menu) {
        try {
            result = menuService.createMenu(menu);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "删除菜单接口", notes = "删除单个菜单")
    @RequestMapping(value = "/deleteMenuByMenuId", method = RequestMethod.POST)
    public JSONObject deleteMenuByMenuId(String menuId) {
        try {
            result = menuService.deleteMenuByMenuId(menuId);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "修改菜单接口", notes = "修改单个菜单")
    @RequestMapping(value = "/modifyMenuByMenuId", method = RequestMethod.POST)
    public JSONObject modifyMenuByMenuId(Menu menu) {
        try {
            result = menuService.modifyMenuByMenuId(menu);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @RequestMapping(value = "/queryMenuByMenuId", method = RequestMethod.POST)
    public JSONObject queryMenuByMenuId(String menuId) {
        try {
            result = menuService.queryMenuByMenuId(menuId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询指定菜单的子菜单树接口", notes = "查询指定菜单的子菜单树")
    @RequestMapping(value = "/querySubMenuByMenuId", method = RequestMethod.POST)
    public JSONObject querySubMenuByMenuId(String menuId) {
        try {
            result = menuService.querySubMenuByMenuId(menuId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询全部菜单树接口", notes = "查询全部菜单树")
    @RequestMapping(value = "/queryAllMenu", method = RequestMethod.POST)
    public JSONObject queryAllMenu() {
        try {
            result = menuService.queryAllMenu();
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询全部菜单树接口", notes = "查询全部菜单树")
    @RequestMapping(value = "/queryAllMenuList", method = RequestMethod.POST)
    public JSONObject queryAllMenuList(Integer j, Integer k) {
		Page<Menu> page = new Page<Menu>(j, k);
		Page<Menu> selectMenuPage = menuService.selectMenuPage(page);
		JSONObject success = ApiResponse.success(selectMenuPage);
        return success;
    }

}
