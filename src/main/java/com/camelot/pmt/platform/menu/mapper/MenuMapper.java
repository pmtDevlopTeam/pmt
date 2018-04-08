package com.camelot.pmt.platform.menu.mapper;

import com.camelot.pmt.platform.menu.model.Menu;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public interface MenuMapper {
	
    int insert(Menu record);
	
    int deleteByPrimaryKey(Integer id);

}
