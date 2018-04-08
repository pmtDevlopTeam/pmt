package com.camelot.pmt.platform.menu.mapper;

import com.camelot.pmt.platform.menu.model.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuMapper {
	
	/**
	 * 增加一个菜单
	 * @param menu
	 * @return 
	 */
	int createMenu(Menu menu);
	
	/**
	 * 根据菜单id 删除一个菜单
	 * @param menuId
	 * @return
	 */
    int deleteMenuByMenuId(String menuId);
	
	/**
	 * 根据菜单id 修改一个菜单
	 * @param Menu
	 * @return
	 */
    int modifyMenuByMenuId(Menu menu);

    /**
     * 根据菜单id 查询一个菜单
     * @param menuId
     * @return
     */
    Menu queryMenuByMenuId(String menuId);
    
    /**
     * 查询全部菜单树
     * @param menuId
     * @return
     */
    List<Menu> queryAllMenu();
}
