package com.camelot.pmt.platform.menu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.menu.mapper.MenuMapper;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.menu.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper MenuMapper;
	
	@Override
	public ApiResponse insert(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

}
