package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.mapper.TaskLogMapper;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskDetail;
import com.camelot.pmt.task.service.TaskOverdueService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
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
     *         JSONObject @throws
     */
    @ApiOperation(value = "查询逾期所有任务+分页+模糊查询", notes = "查询逾期所有任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "beassignUser.username", value = "负责人", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(dataType = "Integer", defaultValue = "1", name = "page", paramType = "query", value = "页码", required = true),
            @ApiImplicitParam(dataType = "Integer", defaultValue = "10", name = "rows", paramType = "query", value = "每页数量", required = true) })
    @RequestMapping(value = "/queryOverdueTask", method = RequestMethod.POST)
    public JSONObject queryOverdueTask(@ApiIgnore Task task, @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer rows) {
        ExecuteResult<PageInfo<Map<String, Object>>> result = new ExecuteResult<PageInfo<Map<String, Object>>>();
        try {
            if (page == null && "".equals(page) || rows == null && "".equals(rows)) {

                return ApiResponse.errorPara();
            }
            // 查询所有逾期列表+pagerhelper分页+模糊
            result = taskService.queryOverdueTask(task, page, rows);
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
     * 
     * @Title: queryOverdueTask @Description: TODO @param @param
     *         page @param @return @return JSONObject @throws
     */

    @ApiOperation(value = "添加任务延期原因,预计时间开始时间添加", notes = "添加任务延期原因,预计时间开始时间添加")
    @RequestMapping(value = "/insertoverduemessage", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务Id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "delayDescribe", value = "任务延期原因", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateStartTime", value = "预计开始时间格式yyyy/MM/dd", required = true, paramType = "form", dataType = "Date") })
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
            @ApiParam(name = "taskId", value = "任务Id", required = true) @RequestParam(required = true) String taskId) {
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

    /**
     * 查询延时任务列表 @Title: delayedTaskReminderList @Description: TODO @param @param
     * leadtime @param @param delaytime @param @return @return JSONObject @throws
     */
    @ApiOperation(value = "查询延时任务列表", notes = "查询延时任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Integer", name = "leadtime", paramType = "query", value = "提前提醒天数", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "delaytime", paramType = "query", value = "延后提醒天数", required = true) })
    @RequestMapping(value = "/delayedTaskReminderList", method = RequestMethod.POST)
    public JSONObject delayedTaskReminderList(@RequestParam(required = true) Integer leadtime,
            @RequestParam(required = true) Integer delaytime) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
        try {
            if (leadtime == null && "".equals(leadtime) || delaytime == null && "".equals(delaytime)) {

                return ApiResponse.errorPara();
            }
            result = taskService.delayedTaskReminderList(leadtime, delaytime);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();

        }
    }

    /**
     * 查询延期任务列表 @Title: deferredTaskRemindersList @Description: TODO @param @param
     * leadtime @param @param delaytime @param @return @return JSONObject @throws
     */
    @ApiOperation(value = "查询延期任务列表", notes = "查询延期任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Integer", name = "delaytime", paramType = "query", value = "延后提醒天数", required = true) })
    @RequestMapping(value = "/deferredTaskRemindersList", method = RequestMethod.POST)
    public JSONObject deferredTaskRemindersList(
            @RequestParam(required = true) Integer delaytime) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
        try {
            if ( delaytime == null && "".equals(delaytime)) {

                return ApiResponse.errorPara();
            }
            result = taskService.deferredTaskRemindersList( delaytime);
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
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
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
     * <p>
     * Description:通过项目ID查询延期延期任务列表
     * </p>
     * 
     * @param
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel}]
     */
    @ApiOperation(value = "通过项目ID查询延期延期任务列表", notes = "通过项目ID查询延期延期任务列表")
    @RequestMapping(value = "/queryOverdueTaskByProjectId", method = RequestMethod.POST)
    public JSONObject queryOverdueTaskByProjectId(
            @ApiParam(name = "projectId", value = "项目Id", required = true) @RequestParam(required = true) String projectId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
        try {
            result = taskService.queryOverdueTaskByProjectId(projectId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

}
