package com.camelot.pmt.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskLogService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
/**
 * 日志管理接口
	* @ClassName: TaskLogController
	* @Description: TODO
	* @author jh
	* @date 2018年4月16日
	*
 */
@RestController
@RequestMapping("/tasklog")
public class TaskLogController {
	
	@Autowired
	private TaskLogService taskLogService;
	
	@ApiOperation(value = "添加任务历史记录", notes = "添加任务历史记录")
	@RequestMapping(value = "/insert-tasklog", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, paramType = "form", dataType = "Long"),
			@ApiImplicitParam(name = "userId", value = "操作人ID", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "operationTime", value = "操作时间", required = true, paramType = "form", dataType = "Date"),
			@ApiImplicitParam(name = "operationButton", value = "操作功能", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "operationDescribe", value = "操作描述", required= true, paramType = "form", dataType = "String")})
	public JSONObject insertTaskLog(@ApiIgnore TaskLog tasklog) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (tasklog == null) {
				return ApiResponse.errorPara();
			}
			// 调用接口进行更新
			result = taskLogService.insertTaskLog(tasklog);
			return ApiResponse.success(result.getResult());
		} catch (Exception e) {
			// 异常
			return ApiResponse.error();
		}
	}
	
	

}
