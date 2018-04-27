package com.camelot.pmt.task.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroConfig;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.IntegrationTest;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.utils.Constant.TaskStatus;


public class TaskPendingControllerTest extends IntegrationTest{

	@Test
	public void testQueryTaskNodeById() { 
		try {
			testLogin();
			MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
			map.add("id",Long.valueOf(35));
			String url = "http://localhost:8080/task/taskPending/queryTaskNodeById";
	        Task task = restTemplate.postForObject(url, map, Task.class);
	        assertTrue("通过主数据的标准化编码进行查询=>queryTaskNodeById执行失败", task!=null&&"黄晓明".equals(task.getTaskName()));
	        System.out.println(task);
		}
		 catch (Exception e) {
			 // TODO: handle exception 
			 e.printStackTrace(); 
		}
	}

	@Test
	public void testQueryMyPendingTaskList() {
		try {
			testLogin();
			User user = (User) ShiroUtils.getSessionAttribute("user");
			Task task = new Task();
			task.setBeassignUser(user);
			String url = "http://localhost:8080/task/taskPending/queryMyPendingTaskList";
	        List<Task> arr = restTemplate.postForObject(url, task, ArrayList.class);
	        assertTrue("通过主数据的标准化编码进行查询=>queryMyPendingTaskList执行失败", arr!=null&&arr.size()>0);
		}
		 catch (Exception e) {
			 // TODO: handle exception 
			 e.printStackTrace(); 
		}
	}
	@Test
	public void testUpdateTaskPendingToRunning() {
		try {
			testLogin();
			MultiValueMap<String,Long> map = new LinkedMultiValueMap<String,Long>();
			map.add("id",Long.valueOf(35));
			String url = "http://localhost:8080/task/taskPending/updateTaskPendingToRunning";
			JSONObject result = restTemplate.postForObject(url, map, JSONObject.class);
			String code = result.getJSONObject("status").getString("code");
			
			String url2 = "http://localhost:8080/task/taskPending/queryTaskNodeById";
			Task task = restTemplate.postForObject(url2, map, Task.class);
	        assertTrue("通过主数据的标准化编码进行查询=>UpdateTaskPendingToRunning接口执行失败", "200".equals(code)&&TaskStatus.RUNING.getValue().equals(task.getStatus()));
		}
		 catch (Exception e) {
			 // TODO: handle exception 
			 e.printStackTrace(); 
		}
	}
	
	@Test
	public void testUpdateTaskPendingToClose() {
		try {
			testLogin();
			MultiValueMap<String,Long> map = new LinkedMultiValueMap<String,Long>();
			map.add("id",Long.valueOf(35));
			String url = "http://localhost:8080/task/taskPending/updateTaskPendingToClose";
			JSONObject result = restTemplate.postForObject(url, map, JSONObject.class);
			String code = result.getJSONObject("status").getString("code");
			
			String url2 = "http://localhost:8080/task/taskPending/queryTaskNodeById";
			Task task = restTemplate.postForObject(url2, map, Task.class);
	        assertTrue("通过主数据的标准化编码进行查询=>UpdateTaskPendingToClose执行失败", "200".equals(code)&&TaskStatus.CLOSE.getValue().equals(task.getStatus()));
	        System.out.println(result);
		}
		 catch (Exception e) {
			 // TODO: handle exception 
			 e.printStackTrace(); 
		}
	}

	@Test
	public void testLogin() throws Exception {
		try { 
		     String url = "http://localhost:8080/login";		     
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();		 
			 map.add("logincode", "liyaohui"); 
			 map.add("password", "123456");
			 JSONObject result = restTemplate.postForObject(url, map, JSONObject.class);
			 String codeStatus = result.getJSONObject("status").getString("code");
		     assertTrue("通过主数据的标准化编码进行查询=>登录失败", "200".equals(codeStatus));
		     User user = (User) ShiroUtils.getSessionAttribute("user");
		     System.out.println("======="+user.getUsername());
		 }
		 catch (Exception e) {
			 // TODO: handle exception 
			 e.printStackTrace(); 
		}
	}
}

