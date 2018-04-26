package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 任务-任务管理接口
 *
 * @author zlh
 * @date 2018/4/10 16:52
 */

@RestController
@RequestMapping(value = "/task/taskManager")
@Api(value = "任务-任务管理接口", description = "任务-任务管理接口")
public class TaskManagerController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskManagerService taskManagerService;

    /**
     * 新增任务接口（新增）
     *
     * @param task
     *            新增的数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/addTask")
    @ApiOperation(value = "新增任务接口", notes = "新增任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "taskType", paramType = "form", value = "任务类型", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "form", value = "任务名称", required = true),
            @ApiImplicitParam(dataType = "String", name = "beassignUser.userId", paramType = "form", value = "负责人id"),
            @ApiImplicitParam(dataType = "String", name = "taskDescribe", paramType = "form", value = "任务描述") })
    public JSONObject addTask(@ApiIgnore Task task) {
        try {
            boolean result = taskManagerService.addTask(task);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 根据id删除任务接口（删除）
     *
     * @param id
     *            任务id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/deleteTaskById")
    @ApiOperation(value = "删除任务接口", notes = "根据id删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true) })
    public JSONObject deleteTaskById(Long id) {
        try {
            boolean result = taskManagerService.deleteTaskById(id);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 评估任务接口（修改）
     *
     * @param task
     *            修改得数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateTask")
    @ApiOperation(value = "评估任务接口", notes = "评估任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "Long", name = "demand.id", paramType = "form", value = "需求id", required = true),
            @ApiImplicitParam(dataType = "Long", name = "estimateHour", paramType = "form", value = "任务预计工时"),
            @ApiImplicitParam(dataType = "String", name = "priority", paramType = "form", value = "任务优先级", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskMileage", paramType = "form", value = "是否里程"),
            @ApiImplicitParam(dataType = "date", name = "estimateStartTime", paramType = "form", value = " 预计开始时间格式yyyy/MM/dd", required = true),
            @ApiImplicitParam(dataType = "date", name = "estimateEndTime", paramType = "form", value = "预计结束时间格式yyyy/MM/dd", required = true),
            @ApiImplicitParam(dataType = "String", name = "beassignUser.userId", paramType = "form", value = "负责人id", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskDescribe", paramType = "form", value = "任务描述") })
    public JSONObject updateTask(@ApiIgnore Task task) {
        try {
            boolean result = taskManagerService.updateTaskByTask(task);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 修改任务需求变更接口（修改）
     *
     * @param task
     *            修改的数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateDemandChangeByTask")
    @ApiOperation(value = "需求是否变更接口", notes = "需求是否变更接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "demandChange", paramType = "form", value = "是否变更 1是 0否", required = true) })
    public JSONObject updateDemandChangeByTask(@ApiIgnore Task task) {
        try {
            boolean result = taskManagerService.updateDemandChangeByTask(task);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 修改任务延期接口（修改）
     *
     * @param task
     *            修改的数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateEstimateStartTim")
    @ApiOperation(value = "修改任务接口-延期", notes = "根据id修改任务预计开始时间")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "date", name = "estimateEndTime", paramType = "form", value = "预计开始时间格式yyyy/MM/dd", required = true),
            @ApiImplicitParam(dataType = "String", name = "delayDescribe", paramType = "form", value = "延期原因") })
    public JSONObject updateEstimateStartTime(@ApiIgnore Task task) {
        try {
            boolean result = taskManagerService.updateEstimateStartTimeById(task);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 任务认领接口（修改）
     *
     * @param id
     *            任务id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateBeAssignUserById")
    @ApiOperation(value = "修改任务接口-认领", notes = "给任务添加负责人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true) })
    public JSONObject updateBeAssignUserById(Long id) {
        try {
            boolean result = taskManagerService.updateBeAssignUserById(id);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 根据id查看任务详情接口（查询）
     *
     * @param id
     *            任务id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @GetMapping(value = "/queryTaskById")
    @ApiOperation(value = "查询任务详情接口", notes = "根据id查询任务详情")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "query", value = "任务id", required = true) })
    public JSONObject queryTaskById(Long id) {
        try {
            Map<String, Object> result = taskManagerService.queryTaskById(id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有任务接口（查询）
     *
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @GetMapping(value = "/queryAllTask")
    @ApiOperation(value = "查询所有任务列表接口", notes = "查询所有任务列表")
    public JSONObject queryAllTask() {
        try {
            Map<String, List<Task>> result = taskManagerService.queryAllTask();
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询任务接口（条件组合查询）
     *
     * @param task
     *            组合查询的数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @GetMapping(value = "/queryTaskByTask")
    @ApiOperation(value = "条件查询任务接口", notes = "类型、截止日期、名称、状态、负责人查询任务")
    @ApiImplicitParams({ @ApiImplicitParam(dataType = "String", name = "taskType", paramType = "query", value = "任务类型"),
            @ApiImplicitParam(dataType = "date", name = "actualEndTime", paramType = "query", value = "截止日期格式yyyy/MM/dd"),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "query", value = "任务名称"),
            @ApiImplicitParam(dataType = "String", name = "status", paramType = "query", value = "任务状态"),
            @ApiImplicitParam(dataType = "User", name = "beassignUser.username", paramType = "query", value = "负责人") })
    public JSONObject queryTaskByTask(@ApiIgnore Task task) {
        try {
            Map<String, List<Task>> result = taskManagerService.queryTaskByTask(task);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有状态为正在进行的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    @GetMapping(value = "/queryTaskByStatusRunning")
    @ApiOperation(value = "查询所有状态为正在进行的任务", notes = "查询所有状态为正在进行的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "int", name = "rows", paramType = "query", value = "显示几行", required = true),
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "query", value = "项目id", required = true)
    })
    public JSONObject queryTaskByStatusRunning(int page, int rows, Long id) {
        try {
            PageInfo<Task> result = taskManagerService.queryTaskByStatusRunning(page, rows, id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有状态为待办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    @GetMapping(value = "/queryTaskByStatusPending")
    @ApiOperation(value = "查询所有状态为待办的任务", notes = "查询所有状态为待办的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "int", name = "rows", paramType = "query", value = "显示几行", required = true),
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "query", value = "项目id", required = true)
    })
    public JSONObject queryTaskByStatusPending(int page, int rows, Long id) {
        try {
            PageInfo<Task> result = taskManagerService.queryTaskByStatusPending(page, rows, id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有状态为已办的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    @GetMapping(value = "/queryTaskByStatusAlready")
    @ApiOperation(value = "查询所有状态为已办的任务", notes = "查询所有状态为已办的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "int", name = "rows", paramType = "query", value = "显示几行", required = true),
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "query", value = "项目id", required = true)
    })
    public JSONObject queryTaskByStatusAlready(int page, int rows, Long id) {
        try {
            PageInfo<Task> result = taskManagerService.queryTaskByStatusAlready(page, rows, id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有状态为关闭的任务
     *
     * @author zlh
     * @date 15:54 2018/4/25
     */
    @GetMapping(value = "/queryTaskByStatusClose")
    @ApiOperation(value = "查询所有状态为关闭的任务", notes = "查询所有状态为关闭的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "int", name = "rows", paramType = "query", value = "显示几行", required = true),
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "query", value = "项目id", required = true)
    })
    public JSONObject queryTaskByStatusClose(int page, int rows, Long id) {
        try {
            PageInfo<Task> result = taskManagerService.queryTaskByStatusClose(page, rows, id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

}
