package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@ControllerAdvice
@RequestMapping(value = "/platform/menu")
@Api(value = "基础平台-菜单管理接口", description = "基础平台-菜单管理接口:提供统一菜单权限管理")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequiresPermissions("platform:menu:createMenu")
    @ApiOperation(value = "创建菜单接口", notes = "创建单个菜单")
    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public JSONObject createMenu(Menu menu) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        menu.setMenuId(uuid);
        return menuService.createMenu(menu);
    }

    @RequiresPermissions("platform:menu:deleteMenuByMenuId")
    @ApiOperation(value = "删除菜单接口", notes = "删除单个菜单")
    @RequestMapping(value = "/deleteMenuByMenuId", method = RequestMethod.POST)
    public JSONObject deleteMenuByMenuId(String menuId) {
        return menuService.deleteMenuByMenuId(menuId);
    }

    @ApiOperation(value = "修改菜单接口", notes = "修改单个菜单")
    @RequestMapping(value = "/modifyMenuByMenuId", method = RequestMethod.POST)
    public JSONObject modifyMenuByMenuId(Menu menu) {
        return menuService.modifyMenuByMenuId(menu);
    }

    @RequiresPermissions("platform:menu:queryMenuByMenuId")
    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @RequestMapping(value = "/queryMenuByMenuId", method = RequestMethod.POST)
    public JSONObject queryMenuByMenuId(String menuId) {
        return menuService.queryMenuByMenuId(menuId);
    }

    @RequiresPermissions("platform:menu:queryAllMenu")
    @ApiOperation(value = "查询全部菜单树接口", notes = "查询全部菜单树")
    @RequestMapping(value = "/queryAllMenu", method = RequestMethod.POST)
    public JSONObject queryAllMenu() {
        return menuService.queryAllMenu();
    }

}
