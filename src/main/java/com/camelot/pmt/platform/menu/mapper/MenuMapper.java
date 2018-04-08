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
	 * @return 1 
	 */
	int createMenu(Menu menu);
	
    int deleteByMenuId(Integer id);

}
