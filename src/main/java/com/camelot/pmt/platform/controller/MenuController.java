package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.util.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author gnerv
 * @Description 基础平台-菜单管理接口
 * @date 2018年4月18日
 */
@RestController
@ControllerAdvice
@RequestMapping(value = "/platform/menu")
@Api(value = "基础平台-菜单管理接口", description = "基础平台-菜单管理接口")
public class MenuController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;

    /**
     * 根据一个菜单对象 创建一个菜单
     * 
     * @param menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "创建菜单接口", notes = "创建单个菜单")
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public JSONObject addMenu(Menu menu) {
        try {
            boolean addMenu = menuService.addMenu(menu);
            if (addMenu) {
                return ApiResponse.success();
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "删除菜单接口", notes = "删除单个菜单")
    @RequestMapping(value = "/deleteMenuByMenuId", method = RequestMethod.POST)
    public JSONObject deleteMenuByMenuId(String menuId) {
        try {
            boolean addMenu = menuService.deleteMenuByMenuId(menuId);
            if (addMenu) {
                return ApiResponse.success();
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "修改菜单接口", notes = "修改单个菜单")
    @RequestMapping(value = "/updateMenuByMenuId", method = RequestMethod.POST)
    public JSONObject updateMenuByMenuId(Menu menu) {
        try {
            boolean addMenu = menuService.updateMenuByMenuId(menu);
            if (addMenu) {
                return ApiResponse.success();
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @RequestMapping(value = "/queryMenuByMenuId", method = RequestMethod.POST)
    public JSONObject queryMenuByMenuId(String menuId) {
        try {
            Menu queryMenuByMenuId = menuService.queryMenuByMenuId(menuId);
            if (queryMenuByMenuId != null) {
                return ApiResponse.success(queryMenuByMenuId);
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

    @ApiOperation(value = "查询指定菜单的子菜单树接口", notes = "查询指定菜单的子菜单树")
    @RequestMapping(value = "/querySubMenuListByMenuId", method = RequestMethod.POST)
    public JSONObject querySubMenuListByMenuId(String menuId) {
        try {
            List<Tree<Menu>> querySubMenuListByMenuId = menuService.querySubMenuListByMenuId(menuId);
            if (querySubMenuListByMenuId != null) {
                return ApiResponse.success(querySubMenuListByMenuId);
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        	return ApiResponse.error();
        }
    }

    @RequiresPermissions("platform:menu:queryAllMenu")
    @ApiOperation(value = "查询全部菜单树接口", notes = "查询全部菜单树")
    @RequestMapping(value = "/queryAllMenuList", method = RequestMethod.POST)
    public JSONObject queryAllMenuList() {
        try {
            List<Tree<Menu>> queryAllMenuList = menuService.queryAllMenuList();
            if (queryAllMenuList != null) {
                return ApiResponse.success(queryAllMenuList);
            }else {
            	return ApiResponse.error();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        	return ApiResponse.error();
        }
    }

}
