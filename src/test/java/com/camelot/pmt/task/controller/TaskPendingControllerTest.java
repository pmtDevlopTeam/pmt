package com.camelot.pmt.task.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.IntegrationTest;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.utils.Constant.TaskStatus;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TaskPendingControllerTest extends IntegrationTest{
	
	@Before
	public void setUp() throws Exception {
//		//模拟用户登录
//		MultiValueMap<String,Object> mapLogin = new LinkedMultiValueMap<String,Object>();
//		mapLogin.add("logincode","liyaohui");
//		mapLogin.add("password","123456");
		String url = "http://localhost:8080/login";
////		MultiValueMap
//	//  一定要设置header
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		//  将提交的数据转换为String
//		//  最好通过bean注入的方式获取ObjectMapper
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, String> params= new HashMap<>();
//		params.put("logincode", "liyaohui");
//		params.put("password", "123456");
//		String value = mapper.writeValueAsString(params);

//		HttpEntity entity = new HttpEntity<>(value,headers);
//		String result = restTemplate.postForObject(url, entity, String.class,params);
//		System.out.println(result);
		
		
		String param = "{\"logincode\":\"liyaohui\",\"password\":\"123456\",\"captcha\":\"\"}";
//		String url = "http://localhost/mirana-ee/app/login";
//		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
//		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//		//  也支持中文
//		params.add("logincode","liyaohui");
//		params.add("password", "123456");
		HttpEntity<String> strEntity = new HttpEntity<String>(param,headers);
		//执行HTTP请求
		String result = restTemplate.postForObject("http://localhost:8080/login?logincode={1}&password={2}",param, String.class, "liyaohui", "123456");
		//  输出结果
		//System.out.println(response);
		//JSONArray status = JSON.parseArray(result.getString("status"));
        //assertTrue("通过主数据的标准化编码进行查询=>登录失败", !"200".equals(status.get(0)));
		
	}

	@Test
	public void testQueryTaskNodeById() { 
		//模拟用户登录
		/*MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		map.add("id",Long.valueOf(35));
		String url = "http://localhost:8080/task/taskPending/queryTaskNodeById";
        Task task = restTemplate.postForObject(url, map, Task.class);
        assertTrue("通过主数据的标准化编码进行查询=>queryTaskNodeById执行失败", task==null||!"黄晓明".equals(task.getTaskName()));
        System.out.println(task);*/
	}

	@Ignore
	@Test
	public void testQueryMyPendingTaskList() {
		User user = (User) ShiroUtils.getSessionAttribute("user");
		Task task = new Task();
		task.setBeassignUser(user);
		String url = "http://localhost:8080/task/taskPending/queryMyPendingTaskList";
        List<Task> arr = restTemplate.postForObject(url, task, ArrayList.class);
        assertTrue("通过主数据的标准化编码进行查询=>queryMyPendingTaskList执行失败", arr==null||arr.size()>0);
	}
	@Ignore
	@Test
	public void testUpdateTaskPendingToRunning() {
		MultiValueMap<String,Long> map = new LinkedMultiValueMap<String,Long>();
		map.add("id",Long.valueOf(35));
		String url = "http://localhost:8080/task/taskPending/updateTaskPendingToRunning";
		String result = restTemplate.postForObject(url, map, String.class);
		//JSONArray status = JSON.parseArray(result.getString("status"));
		
		String url2 = "http://localhost:8080/task/taskPending/queryTaskNodeById";
		Task task = restTemplate.postForObject(url2, map, Task.class);
        //assertTrue("通过主数据的标准化编码进行查询=>UpdateTaskPendingToRunning接口执行失败", !"200".equals(status.get(0))||!TaskStatus.RUNING.getValue().equals(task.getStatus()));
        //System.out.println(result);
	}
	@Ignore
	@Test
	public void testUpdateTaskPendingToClose() {
		MultiValueMap<String,Long> map = new LinkedMultiValueMap<String,Long>();
		map.add("id",Long.valueOf(35));
		String url = "http://localhost:8080/task/taskPending/updateTaskPendingToClose";
		JSONObject result = restTemplate.postForObject(url, map, JSONObject.class);
		JSONArray status = JSON.parseArray(result.getString("status"));
		
		String url2 = "http://localhost:8080/task/taskPending/queryTaskNodeById";
		Task task = restTemplate.postForObject(url2, map, Task.class);
        assertTrue("通过主数据的标准化编码进行查询=>UpdateTaskPendingToClose执行失败", !"200".equals(status.get(0))||!TaskStatus.CLOSE.getValue().equals(task.getStatus()));
        System.out.println(result);
	}

}

