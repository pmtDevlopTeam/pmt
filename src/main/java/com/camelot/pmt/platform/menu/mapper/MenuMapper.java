package com.camelot.pmt.platform.menu.mapper;

import com.camelot.pmt.platform.menu.model.Menu;
import org.springframework.stereotype.Repository;

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

}
