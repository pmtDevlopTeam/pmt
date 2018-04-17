package com.camelot.pmt.platform.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;

/**
 * 权限菜单服务接口类
 *
 * @author pmt
 * @since 2018-04-08
 */
public interface MenuService {

    /**
     * 增加一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    ExecuteResult<Menu> addMenu(Menu menu);

    /**
     * 删除一个菜单
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    ExecuteResult<Menu> deleteMenuByMenuId(String menuId);

    /**
     * 修改一个菜单
     *
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
    ExecuteResult<Menu> updateMenuByMenuId(Menu menu);

    /**
     * 查询一个菜单
     *
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
    ExecuteResult<Menu> queryMenuByMenuId(String menuId);

    /**
     * 查询一个菜单
     *
     * @param String id
     * @return ExecuteResult<Menu>
     */
    ExecuteResult<Menu> queryMenuById(String id);

    /**
     * 菜单分页查询
     * @param page
     * @param state
     * @return
     */
    List<Menu> selectMenuList(Menu menu);
}
