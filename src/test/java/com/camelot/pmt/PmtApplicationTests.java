package com.camelot.pmt;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.menu.model.Menu;
import com.camelot.pmt.platform.menu.service.MenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.camelot.pmt.platform.*.mapper")
public class PmtApplicationTests {

	@Autowired
	MenuService menuService;
	
	@Test
	public void contextLoads() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Menu menu = new Menu(null,uuid,"0","测试按钮","1","/menu","hehehe","test.ico","0",1,null,null);
		JSONObject createMenu = menuService.createMenu(menu);
		System.out.println(createMenu);
	}
	
	

}
