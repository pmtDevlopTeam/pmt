package com.camelot.pmt.platform.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.Menu;

/**
 * <p>
 * 权限菜单服务接口类
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuService {

    /**
     * 根据一个菜单对象 增加一个菜单
     * 
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
	ExecuteResult<Menu> createMenu(Menu menu);

    /**
     * 根据菜单id 删除一个菜单
     * 
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
	ExecuteResult<Menu> deleteMenuByMenuId(String menuId);

    /**
     * 根据菜单id 修改一个菜单
     * 
     * @param Menu menu
     * @return ExecuteResult<Menu>
     */
	ExecuteResult<Menu> modifyMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单
     * 
     * @param String menuId
     * @return ExecuteResult<Menu>
     */
	ExecuteResult<Menu> queryMenuByMenuId(String menuId);

    /**
     * 查询全部菜单树
     * 
     * @param 
     * @return ExecuteResult<List<Menu>>
     */
	ExecuteResult<List<Menu>> queryAllMenu();
    
    /**
     * 根据菜单id 查询指定菜单的子菜单树
     * 
     * @param String menuId
     * @return ExecuteResult<List<Menu>>
     */
	ExecuteResult<List<Menu>> querySubMenuByMenuId(String menuId);
    
    /**
     * 菜单分页测试
     * @param page
     * @param state
     * @return
     */
    Page<Menu> selectMenuPage(Page<Menu> page);
}
