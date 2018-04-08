package com.camelot.pmt.platform.menu.controller;

import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.menu.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/platform/menu")
@Api(value="基础平台-菜单管理接口", consumes="基础平台-菜单管理接口:提供统一菜单权限管理")
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	//@RequiresPermissions("platform:menu:menu")
	@ApiOperation(value="创建菜单接口", notes="创建单个菜单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "menuName", value = "菜单名称", required = true, dataType = "String"),
        @ApiImplicitParam(name = "menuType", value = "菜单类型", required = true, dataType = "String"),
        @ApiImplicitParam(name = "menuURL", value = "菜单请求地址", required = true, dataType = "String"),
        @ApiImplicitParam(name = "menuPermission", value = "菜单权限配置", required = true, dataType = "String"),
        @ApiImplicitParam(name = "menuIcon", value = "菜单图标", required = true, dataType = "String"),
        @ApiImplicitParam(name = "state", value = "菜单状态", required = true, dataType = "String")
	})
	@RequestMapping(value="/createMenu", method=RequestMethod.POST)
	public JSONObject createMenu(Menu menu) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		menu.setMenuId(uuid);
		return menuService.createMenu(menu);
	}
}
