package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.MenuService;
import com.camelot.pmt.platform.shiro.ShiroUtils;

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
    @Autowired
    ShiroUtils shiroUtils;

    @ApiOperation(value = "创建菜单接口", notes = "创建单个菜单")
    @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
    public JSONObject createMenu(Menu menu) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        menu.setMenuId(uuid);
        return menuService.createMenu(menu);
    }

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

    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @RequestMapping(value = "/queryMenuByMenuId", method = RequestMethod.POST)
    public JSONObject queryMenuByMenuId(String menuId) {
        return menuService.queryMenuByMenuId(menuId);
    }

    @ApiOperation(value = "查询全部菜单树接口", notes = "查询全部菜单树")
    @RequestMapping(value = "/queryAllMenu", method = RequestMethod.POST)
    public JSONObject queryAllMenu() {
    	User user = (User)ShiroUtils.getSessionAttribute("user");
        return menuService.queryAllMenu();
    }

    @ApiOperation(value = "查询指定菜单的子菜单树接口", notes = "查询指定菜单的子菜单树")
    @RequestMapping(value = "/queryListMenuByMenuId", method = RequestMethod.POST)
    public JSONObject queryListMenuByMenuId(String menuId) {
        return menuService.queryListMenuByMenuId(menuId);
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
