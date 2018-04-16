package com.camelot.pmt.task.controller;

//import org.junit.Ignore;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.camelot.pmt.task.service.TaskOverdueService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @ClassName: TaskController
 * @Description: TODO
 * @author zhangao
 * @date 2018年4月9日
 *
 */
@RestController
@RequestMapping("/task")
@Api(value = "延期任务管理接口", description = "延期任务管理接口")
public class TaskOverdueController {

	@Autowired
	private TaskOverdueService taskService;

	/**
	 * 
	 * @Title: queryUserAll @Description: TODO查询所有任务 @param @return @return
	 * JSONObject @throws
	 */
	@ApiOperation(value = "查询逾期所有任务", notes = "查询逾期所有任务")
	@RequestMapping(value = "/queryOverdueTask", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int") })
	public JSONObject queryOverdueTask(@ApiIgnore Pager page) {
		ExecuteResult<DataGrid<Task>> result = new ExecuteResult<DataGrid<Task>>();
		try {
			if (page == null) {

				return ApiResponse.errorPara();
			}
			result = taskService.queryOverdueTask(page);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();

		}
	}

	/**
	 * <p>
	 * Description:[查询单个延期任务详情]
	 * </p>
	 * 
	 * @param
	 * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
	 */
	@ApiOperation(value = "根据taskId查询单个延期任务详情信息", notes = "查询单个延期任务详情信息")
	@RequestMapping(value = "/queryOverdueTaskDetailByTaskId", method = RequestMethod.POST)
	public JSONObject queryOverdueTaskDetailByTaskId(
			@ApiParam(name = "taskId", value = "延期任务Id", required = true) @RequestParam(required = true) String taskId) {
		ExecuteResult<TaskDetail> result = new ExecuteResult<TaskDetail>();
		try {
			result = taskService.queryOverdueTaskDetailByTaskId(taskId);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
	 * 添加任务延期原因,预计时间开始时间添加
	 * @Title: queryOverdueTask @Description: TODO @param @param
	 * page @param @return @return JSONObject @throws
	 */

	@ApiOperation(value = "添加任务延期原因,预计时间开始时间添加", notes = "添加任务延期原因,预计时间开始时间添加")
	@RequestMapping(value = "/insertoverduemessage", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "abnormalDescribe", value = "任务延期原因", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "estimateStartTime", value = "预计开始时间", required = true, paramType = "form", dataType = "String") })
	public JSONObject insertOverduMeessage(@ApiIgnore Task task) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (task.getId() == null && "".equals(task.getId()) && task.getId() != 0) {
				return ApiResponse.errorPara();
			}
			// 调用接口进行更新
			result = taskService.insertOverduMessage(task);
			return ApiResponse.success(result.getResult());
		} catch (Exception e) {
			// 异常
			return ApiResponse.error();
		}
	}
	
	/**
	 * 根据UserId查询用户是否有延期任务(弹框提示)
	* @Title: queryOverdueTaskDetailByUserId
	* @Description: TODO
	* @param @param userId 
	* @param @return
	* @return JSONObject 
	* @throws
	 */
	@ApiOperation(value = "根据UserId查询用户是否有延期任务", notes = "根据UserId查询用户是否有延期任务")
	@RequestMapping(value = "/queryOverdueTaskUserId", method = RequestMethod.GET)
	public JSONObject queryOverdueTaskByUserId(
			@ApiParam(name = "userId", value = "用户Id", required = true) @RequestParam(required = true) String userId) {
		ExecuteResult<Integer> result = new ExecuteResult<Integer>();
		try {
			result = taskService.queryOverdueTaskUserId(userId);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

}
