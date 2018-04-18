package com.camelot.pmt.task.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.camelot.pmt.task.service.TaskOverdueService;
import com.github.pagehelper.PageInfo;
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
	public JSONObject queryOverdueTask(
			@ApiParam(name = "page", value = "页码", required = true) @RequestParam(required = true) Integer page,
			@ApiParam(name = "rows", value = "每页数量", required = true) @RequestParam(required = true) Integer rows) {
		ExecuteResult<PageInfo<Task>> result = new ExecuteResult<PageInfo<Task>>();
		try {
			if (page == null && "".equals(page) || rows == null && "".equals(rows)) {

				return ApiResponse.errorPara();
			}
			//查询所有逾期列表+pagerhelper分页
			result = taskService.queryOverdueTask(page,rows);
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
	
	
	@ApiOperation(value = "根据任务id修改状态(延期-进行中)", notes = "根据任务id修改状态(延期-进行中)")
	@RequestMapping(value = "/update-taskoverdue-status", method = RequestMethod.POST)
	public JSONObject updateTaskOverdueStatus(
	@ApiParam(name = "taskId", value = "任务Id", required = true) @RequestParam(required = true) String taskId)
	{
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (StringUtils.isEmpty(taskId)) {
				return ApiResponse.errorPara();
			}
			// 调用接口进行更新
			result = taskService.updateTaskOverdueStatus(taskId);
			return ApiResponse.success(result.getResult());
		} catch (Exception e) {
			// 异常
			return ApiResponse.error();
		}
	}
}
