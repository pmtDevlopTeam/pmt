package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.ExecuteResult;
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
        ExecuteResult<List<TaskManager>> result = null;
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
    @ApiOperation(value = "条件查询任务接口", notes = "根据项目、类型、截止日期、名称、状态、异常状态、负责人查询任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Project", name = "project.proName", value = "项目名称", required = false),
            @ApiImplicitParam(dataType = "String", name = "taskType", value = "任务类型", required = false),
            @ApiImplicitParam(dataType = "java.util.Date", name = "estimateEndTime", value = "截止日期格式0000-00-00", required = false),
            @ApiImplicitParam(dataType = "String", name = "taskName", value = "任务名称", required = false),
            @ApiImplicitParam(dataType = "String", name = "status", value = "任务状态", required = false),
            @ApiImplicitParam(dataType = "String", name = "abnormalStatus", value = "任务异常状态", required = false),
            @ApiImplicitParam(dataType = "UserModel", name = "beassignUser.userId", value = "负责人", required = false),
            @ApiImplicitParam(dataType = "String", name = "还有好多", value = "还有好多参数未定", required = false)
    })
    public JSONObject queryTaskByTask(@ApiIgnore TaskManager taskManager) {
        ExecuteResult<List<TaskManager>> result = null;
        try {
            result = taskManagerService.queryTaskByTask(taskManager);
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
    public JSONObject insertTask(TaskManager taskManager, MultipartFile file) {
        ExecuteResult<String> result = null;
        try {
            result = taskManagerService.insertTask(taskManager, file);
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
            @ApiImplicitParam(dataType = "Date", name = "estimateEndTime", value = "预计开始时间格式yyyy-MM-dd", required = true)
    })
    public JSONObject updateEstimateStartTime(@ApiIgnore TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = taskManagerService.updateEstimateStartTimeById(taskManager);
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

    @GetMapping(value = "/queryTaskById")//zlh
    @ApiOperation(value = "查询任务详情接口", notes = "根据id查询任务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", value = "任务id", required = true),
    })
    public JSONObject queryTaskById(Long id) {
        ExecuteResult<TaskManager> result = new ExecuteResult<TaskManager>();
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
    public JSONObject editTask(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = taskManagerService.updateTaskByTask(taskManager);
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
    public JSONObject updateDemandChangeByTask(TaskManager taskManager) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = taskManagerService.updateDemandChangeByTask(taskManager);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }
}
