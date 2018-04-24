package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.*;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskManagerService;
import com.camelot.pmt.task.service.TaskRunningService;
import com.camelot.pmt.task.utils.Constant;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author myp
 * @Description: 我的控制台-正在进行任务管理
 * @date 2018-04-11
 */

@RestController
@RequestMapping("/task/taskRunning")
@Api(value = "我的工作台-正在进行-接口", description = "我的工作台-正在进行-接口")
public class TaskRunningController {

    @Autowired
    private TaskRunningService taskRunningService;

    @Autowired
    private TaskManagerService taskManagerService;

    /**
     * 查询所有正在进行的任务
     *
     * @param page，
     *            int rows
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询所有正在进行的任务", notes = "查询所有正在进行的任务")
    @RequestMapping(value = "/queryTaskRunning", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ProjectMain", name = "project.id", paramType = "query", value = "项目编号"),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "query", value = "任务名称"),
            @ApiImplicitParam(dataType = "String", name = "taskNum", paramType = "query", value = "任务编号"),
            @ApiImplicitParam(dataType = "Demand", name = "demand.id", paramType = "query", value = "需求编号") })
    public JSONObject queryTaskRunning(@ApiIgnore Task task) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            task.setBeassignUser(user);
            result = taskRunningService.queryTaskRunning(task);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 根据id查询单个任务明细
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据id查询单个任务明细", notes = "根据id查询单个任务明细")
    @RequestMapping(value = "user/queryTaskById", method = RequestMethod.POST)
    public JSONObject queryTaskById(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        try {
            Map<String, Object> result = taskManagerService.queryTaskById(id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 我的任务转为关闭
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "我的任务转为关闭", notes = "我的任务转为关闭")
    @RequestMapping(value = "/updateTaskToClose", method = RequestMethod.GET)
    public JSONObject updateTaskToClose(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的任务为关闭
            result = taskRunningService.updateRunningToClose(id);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 我的正在进行任务转为已完成、实现完成功能
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "我的正在进行任务转为已完成、实现完成功能", notes = "我的正在进行任务转为已完成、实现完成功能")
    @RequestMapping(value = "/updateTaskRunningToAlready", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "Long", name = "infactHour", paramType = "form", value = "任务实际工时", required = true),
            @ApiImplicitParam(dataType = "date", name = "actualEndTime", paramType = "form", value = " 实际完成时间yyyy/MM/dd hh:MM:ss", required = true),
            @ApiImplicitParam(dataType = "String", name = "attachmentUrl", paramType = "form", value = "附件的路径url", required = true),
            @ApiImplicitParam(dataType = "String", name = "attachmentTile", paramType = "form", value = "附件名称", required = true) })
    public JSONObject updateTaskRunningToAlready(@ApiIgnore Task task, @ApiIgnore TaskFile taskFile) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 更新我的正在进行任务为完成
            result = taskRunningService.updateRunningToAlready(task, taskFile);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

}
