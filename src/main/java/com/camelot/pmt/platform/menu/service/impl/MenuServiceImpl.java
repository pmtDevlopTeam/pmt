package com.camelot.pmt.platform.menu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.menu.mapper.MenuMapper;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.menu.service.MenuService;


@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper MenuMapper;
	
	@Override
	public JSONObject createMenu(Menu menu) {
		int createMenu = MenuMapper.createMenu(menu);
		if(createMenu == 1) {
			return ApiResponse.success();
		}
		return ApiResponse.error();
	}

	@Override
	public JSONObject deleteMenuByMenuId(String menuId) {
		int deleteMenuByMenuId = MenuMapper.deleteMenuByMenuId(menuId);
		if(deleteMenuByMenuId == 1) {
			return ApiResponse.success();
		}
		return ApiResponse.error();
	}

	@Override
	public JSONObject modifyMenuByMenuId(Menu menu) {
		int modifyMenuByMenuId = MenuMapper.modifyMenuByMenuId(menu);
		if(modifyMenuByMenuId == 1) {
			return ApiResponse.success();
		}
		return ApiResponse.error();
	}
	
	

}
