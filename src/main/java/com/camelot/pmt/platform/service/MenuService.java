package com.camelot.pmt.platform.service;

import com.alibaba.fastjson.JSONObject;
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
     * @param menu
     * @return
     */
    JSONObject createMenu(Menu menu);

    /**
     * 根据菜单id 删除一个菜单
     * 
     * @param menuId
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
}
