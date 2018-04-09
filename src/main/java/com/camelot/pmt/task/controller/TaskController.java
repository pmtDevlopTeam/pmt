package com.camelot.pmt.task.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.camelot.pmt.task.service.TaskService;

/**
 * 
	* @ClassName: TaskController
	* @Description: TODO
	* @author zhangao
	* @date 2018年4月9日
	*
 */
@RestController 
public class TaskController {
	
	@Autowired
	private TaskService taskService;

}	
	