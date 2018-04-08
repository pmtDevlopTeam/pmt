package com.camelot.pmt;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	
	@Test
	public void roleTest() {
		Rolee rolee1 = new Rolee("1","0","1");
		Rolee rolee2 = new Rolee("2","0","2");
		Rolee rolee3 = new Rolee("3","1","3");
		Rolee rolee4 = new Rolee("4","3","4");
		Rolee rolee5 = new Rolee("5","2","5");
		
		ArrayList<Rolee> arrayList = new ArrayList<Rolee>();
		arrayList.add(rolee1);
		arrayList.add(rolee2);
		arrayList.add(rolee3);
		arrayList.add(rolee4);
		arrayList.add(rolee5);
		
		HashMap<String, Rolee> hashMap = new HashMap<>();
		hashMap.put(rolee1.getRid(), rolee1);
		hashMap.put(rolee2.getRid(), rolee2);
		hashMap.put(rolee3.getRid(), rolee3);
		hashMap.put(rolee4.getRid(), rolee4);
		hashMap.put(rolee5.getRid(), rolee5);
		
		ArrayList<Rolee> rootList = new ArrayList<Rolee>();
		
		
		
		for (Rolee rolee : arrayList) {
			if("0".equals(rolee.getPid())) {
				rootList.add(rolee);
			}else {
				boolean add = hashMap.get(rolee.getPid()).getArrayList().add(rolee);
			}
		}
		
		System.out.println(rootList.toString());
		
	}

}

class Rolee {
	public String rid;
	public String pid;
	public String name;
	
	public ArrayList<Rolee> arrayList = new ArrayList<Rolee>();
	
	public Rolee() {
		super();
	}	
	public Rolee(String rid, String pid, String name) {
		super();
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Rolee> getArrayList() {
		return arrayList;
	}
	public void setArrayList(ArrayList<Rolee> arrayList) {
		this.arrayList = arrayList;
	}
	@Override
	public String toString() {
		return "Rolee [rid=" + rid + ", pid=" + pid + ", name=" + name + "]";
	}
	
}


