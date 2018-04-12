package com.camelot.pmt.platform.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.camelot.pmt.platform.model.Menu;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuService {

    /**
     * 增加一个菜单
     * 
     * @param Menu menu
     * @return
     */
    JSONObject createMenu(Menu menu);

    /**
     * 根据菜单id 删除一个菜单
     * 
     * @param String menuId
     * @return
     */
    JSONObject deleteMenuByMenuId(String menuId);

    /**
     * 根据菜单id 修改一个菜单
     * 
     * @param menu
     * @return
     */
    JSONObject modifyMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单
     * 
     * @param menu
     * @return
     */
    JSONObject queryMenuByMenuId(String menuId);

    /**
     * 查询全部菜单树
     * 
     * @param menu
     * @return
     */
    JSONObject queryAllMenu();
    
    /**
     * 根据菜单id 查询指定菜单的子菜单树
     * 
     * @param menuId
     * @return
     */
    JSONObject queryListMenuByMenuId(String menuId);
    
    
    /**
     * 菜单分页测试
     * @param page
     * @param state
     * @return
     */
    Page<Menu> selectMenuPage(Page<Menu> page);
}
