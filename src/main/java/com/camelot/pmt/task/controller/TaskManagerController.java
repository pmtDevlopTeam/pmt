package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskManager;
import com.camelot.pmt.task.service.TaskManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @author zlh
 * @date 2018/4/10 16:52
 */

@RestController
@RequestMapping(value = "/task/taskManager")
@Api(value = "任务管理接口", description = "任务管理接口")
public class TaskManagerController {
    @Autowired
    private TaskManagerService taskManagerService;

    @GetMapping(value = "/queryAllTask")
    @ApiOperation(value = "查询所有任务列表接口", notes = "查询所有任务列表")
    public JSONObject queryAllTask() {
        ExecuteResult<List<Task>> result = null;
        try {
            result = taskManagerService.queryAllTask();
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @GetMapping(value = "/queryTaskByTask")
    @ApiOperation(value = "条件查询任务接口", notes = "类型、截止日期、名称、状态、负责人查询任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "taskType", value = "任务类型", required = false),
            @ApiImplicitParam(dataType = "Date", name = "actualEndTime", value = "截止日期格式yyyy-MM-dd", required = false),
            @ApiImplicitParam(dataType = "String", name = "taskName", value = "任务名称", required = false),
            @ApiImplicitParam(dataType = "String", name = "status", value = "任务状态", required = false),
            @ApiImplicitParam(dataType = "User", name = "beassignUser.username", value = "负责人", required = false),
    })
    public JSONObject queryTaskByTask(@ApiIgnore Task task) {
        ExecuteResult<List<Task>> result = null;
        try {
            result = taskManagerService.queryTaskByTask(task);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/insertTask")
    @ApiOperation(value = "新增任务接口", notes = "新增任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "User", name = "beassignUser.username", value = "负责人", required = true),
            @ApiImplicitParam(dataType = "Long", name = "demand.id", value = "相关需求", required = false),
            @ApiImplicitParam(dataType = "String", name = "taskType", value = "任务类型", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskName", value = "任务名称", required = true),
            @ApiImplicitParam(dataType = "Date", name = "estimateStartTime", value = " 预计开始时间格式yyyy-MM-dd", required = true),
            @ApiImplicitParam(dataType = "Date", name = "estimateEndTime", value = "预计结束时间格式yyyy-MM-dd", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskDescribe", value = "任务描述", required = false)
    })
    public JSONObject insertTask(Task task, MultipartFile file) {
        ExecuteResult<String> result = null;
        try {
            result = taskManagerService.insertTask(task, file);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateEstimateStartTim")
    @ApiOperation(value = "修改任务接口-延期", notes = "根据id修改任务预计开始时间")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "Date", name = "estimateEndTime", value = "预计开始时间格式yyyy-MM-dd", required = true),
            @ApiImplicitParam(dataType = "String", name = "delayDescribe", value = "延期原因", required = false)
    })
    public JSONObject updateEstimateStartTime(@ApiIgnore Task task) {
        ExecuteResult<String> result = null;
        try {
            result = taskManagerService.updateEstimateStartTimeById(task);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateBeAssignUserById")
    @ApiOperation(value = "修改任务接口-指派", notes = "给任务添加负责人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "userId", value = "被指派人id", required = true),
            @ApiImplicitParam(dataType = "boolean", name = "isAssignAll", value = "是否一并指派子任务", required = true)
    })
    public JSONObject updateBeAssignUserById(Long id, String userId, boolean isAssignAll) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = taskManagerService.updateBeAssignUserById(id, userId, isAssignAll);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @GetMapping(value = "/queryTaskById")
    @ApiOperation(value = "查询任务详情接口", notes = "根据id查询任务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", value = "任务id", required = true),
    })
    public JSONObject queryTaskById(Long id) {
        ExecuteResult<Map<String, Object>> result = null;
        try {
            result = taskManagerService.queryTaskById(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/deleteTaskById")
    @ApiOperation(value = "删除任务接口", notes = "根据id删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", value = "任务id", required = true)
    })
    public JSONObject deleteTaskById(Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = taskManagerService.deleteTaskById(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/editTask")
    @ApiOperation(value = "编辑任务接口", notes = "编辑任务接口")
    public JSONObject editTask(Task task) {
        ExecuteResult<String> result = null;
        try {
            result = taskManagerService.updateTaskByTask(task);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    @PostMapping(value = "/updateDemandChangeByTask")
    @ApiOperation(value = "确认变更接口", notes = "确认变更接口")
    public JSONObject updateDemandChangeByTask(Task task) {
        ExecuteResult<String> result = null;
        try {
            result = taskManagerService.updateDemandChangeByTask(task);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }
}
