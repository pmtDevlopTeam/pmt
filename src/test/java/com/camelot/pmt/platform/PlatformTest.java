package com.camelot.pmt.platform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.plugins.Page;
import com.camelot.pmt.platform.model.Menu;
import com.camelot.pmt.platform.service.MenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformTest {
	
	@Autowired
	MenuService menuService;
	
	@Test
	public void MenuTest() {
		Page<Menu> page = new Page<Menu>(0, 3);
		Page<Menu> selectMenuPage = menuService.selectMenuPage(page);
		System.out.println(selectMenuPage);
	}
	
	
	
	
}
