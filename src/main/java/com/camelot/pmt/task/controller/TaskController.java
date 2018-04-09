package com.camelot.pmt.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
	* @ClassName: TaskController
	* @Description: TODO
	* @author zhangao
	* @date 2018年4月9日
	*
 */

import com.camelot.pmt.task.service.TaskService;
@Controller 
public class TaskController {
	
	@Autowired
	private TaskService taskService;

}	
	