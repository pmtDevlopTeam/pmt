package com.camelot.pmt.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
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

/**
 * @author pmt
 * @Description: 基础平台-菜单管理接口
 * 注意：所有方法按照增删改查顺序添加
 * @date 2018-04-11
 */
@RestController
@Api(value = "基础平台-菜单管理接口", description = "基础平台-菜单管理接口")
@RequestMapping(value = "/platform/menu")
public class MenuController {

    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;

    /**
     * 根据一个菜单对象 创建一个菜单(新增)
     * 注意：新增方法以addXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "创建菜单接口", notes = "创建单个菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "menuId", value = "菜单业务id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "菜单上级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuType", value = "菜单类型（1 目录 2 菜单 3 按钮）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuUrl", value = "菜单请求地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuPermission", value = "菜单授权模块方法", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuIcon", value = "菜单图标", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "菜单状态", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "createUserId", value = "创建人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyTime", value = "修改时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人", required = false, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "/addMenu")
    public JSONObject addMenu(@ApiIgnore Menu menu) {
        try {
            result = menuService.addMenu(menu);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 删除菜单接口（删除）
     * 注意：删除方法以deleteXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "删除菜单接口", notes = "删除单个菜单")
    @ApiParam(name = "menuId", value = "菜单业务di", required = false, paramType = "form", dataType = "String")
    @PostMapping(value = "/deleteMenuByMenuId")
    public JSONObject deleteMenuByMenuId(String menuId) {
        try {
            result = menuService.deleteMenuByMenuId(menuId);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 修改菜单接口（修改）
     * 注意：修改方法以updateXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "修改菜单接口", notes = "修改单个菜单")
    ApiImplicitParams({
        @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "query", dataType = "Long"),
        @ApiImplicitParam(name = "menuId", value = "菜单业务id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "parentId", value = "菜单上级id", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "menuName", value = "菜单名称", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "menuType", value = "菜单类型（1 目录 2 菜单 3 按钮）", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "menuUrl", value = "菜单请求地址", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "menuPermission", value = "菜单授权模块方法", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "menuIcon", value = "菜单图标", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "state", value = "菜单状态", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sortNum", value = "排序号", required = false, paramType = "query", dataType = "Integer"),
        @ApiImplicitParam(name = "createTime", value = "创建时间", required = false, paramType = "query", dataType = "Date"),
        @ApiImplicitParam(name = "createUserId", value = "创建人", required = false, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "modifyTime", value = "修改时间", required = false, paramType = "query", dataType = "Date"),
        @ApiImplicitParam(name = "modifyUserId", value = "修改人", required = false, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "/updateMenuByMenuId")
    public JSONObject updateMenuByMenuId(@ApiIgnore Menu menu) {
        try {
            result = menuService.updateMenuByMenuId(menu);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询菜单接口（查询）
     * 注意：查询方法以queryXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @PostMapping(value = "/queryMenuByMenuId")
    public JSONObject queryMenuByMenuId(@ApiParam(name = "menuId", value = "菜单业务di", required = false, paramType = "form", dataType = "String")String menuId) {
        try {
            result = menuService.queryMenuByMenuId(menuId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 举例get请求方式(查询)
     * 注意：查询方法以queryXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询菜单接口", notes = "查询单个菜单")
    @GetMapping(value = "/queryMenuByMenuId")
    public JSONObject queryMenuById(@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
        try {
            result = menuService.queryMenuById(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 菜单分页查询(查询)
     * 注意：查询方法以queryXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询全部菜单分页接口", notes = "查询全部菜单分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "menuId", value = "菜单业务id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "菜单上级id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuName", value = "菜单名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuType", value = "菜单类型（1 目录 2 菜单 3 按钮）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuUrl", value = "菜单请求地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuPermission", value = "菜单授权模块方法", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuIcon", value = "菜单图标", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "菜单状态", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortNum", value = "排序号", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "createUserId", value = "创建人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyTime", value = "修改时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人", required = false, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "/queryAllMenuList")
    public JSONObject queryAllMenuList(@ApiIgnore Menu menu， @RequestParam(defaultValue = "1") Integer pageSize,@RequestParam(defaultValue = "10") Integer currentPage) {

        try {
            List<Menu> list = menuService.selectMenuList(menu);
            PageInfo<Menu> result = new PageInfo<Menu>(list);

            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();

    }
}
