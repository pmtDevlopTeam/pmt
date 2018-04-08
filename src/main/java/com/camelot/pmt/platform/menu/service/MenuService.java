package com.camelot.pmt.platform.menu.service;

import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.menu.model.Menu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuService {
	
	/**
	 * 增加一个菜单
	 * @param menu
	 * @return 
	 */
	ApiResponse insert(Menu menu);

}
