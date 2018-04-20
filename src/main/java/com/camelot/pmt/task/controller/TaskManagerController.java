package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskManagerService;
import com.camelot.pmt.task.service.TaskRunningService;
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
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            @ApiImplicitParam(dataType = "User", name = "beassignUser.userId", paramType = "form", value = "负责人", required = true),
            @ApiImplicitParam(dataType = "Long", name = "demand.id", paramType = "form", value = "相关需求"),
            @ApiImplicitParam(dataType = "String", name = "taskType", paramType = "form", value = "任务类型", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "form", value = "任务名称", required = true),
            @ApiImplicitParam(dataType = "date", name = "estimateStartTime", paramType = "form", value = " 预计开始时间格式yyyy/MM/dd", required = true),
            @ApiImplicitParam(dataType = "date", name = "estimateEndTime", paramType = "form", value = "预计结束时间格式yyyy/MM/dd", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskDescribe", paramType = "form", value = "任务描述") })
    public JSONObject addTask(@ApiIgnore Task task, MultipartFile file) {
        try {
            boolean result = taskManagerService.insertTask(task, file);
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
     * 编辑任务接口（修改）
     *
     * @param task
     *            修改得数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateTask")
    @ApiOperation(value = "编辑任务接口", notes = "编辑任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "form", value = "任务名称"),
            @ApiImplicitParam(dataType = "date", name = "estimateStartTime", paramType = "form", value = " 预计开始时间格式yyyy/MM/dd"),
            @ApiImplicitParam(dataType = "date", name = "estimateEndTime", paramType = "form", value = "预计结束时间格式yyyy/MM/dd"),
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
     * 修改任务确认变更接口（修改）
     *
     * @param task
     *            修改的数据
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateDemandChangeByTask")
    @ApiOperation(value = "确认变更接口", notes = "确认变更接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "demandChange", paramType = "form", value = "是否变更", required = true) })
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
     * 修改任务指派接口-验证是否有普通权限（任务的创建人、负责人）（修改）
     *
     * @param id
     *            任务id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateBeAssignUserById")
    @ApiOperation(value = "修改任务接口-指派", notes = "给任务添加负责人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "userId", paramType = "form", value = "被指派人id", required = true) })
    public JSONObject updateBeAssignUserById(Long id, String userId, HttpServletResponse response,
            HttpSession session) {
        try {
            boolean result = taskManagerService.updateBeAssignUserById(id, userId);
            if (result) {
                return ApiResponse.success();
            }
            /**
             * 因为指派的权限要求 创建人和负责人 和项目经理角色可以操作 正常逻辑是无法通过
             */
            session.setAttribute("id", id);
            session.setAttribute("userId", userId);
            response.sendRedirect("/task/taskManager/updateBeAssignUserByIdCheckPower");
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 修改任务指派接口-验证是否有项目经理权限（修改）
     *
     * @param session
     *            session
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @PostMapping(value = "/updateBeAssignUserByIdCheckPower")
    @ApiOperation(value = "修改任务接口-指派", notes = "给任务添加负责人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Long", name = "id", paramType = "form", value = "任务id", required = true),
            @ApiImplicitParam(dataType = "String", name = "userId", paramType = "form", value = "被指派人id", required = true) })
    public JSONObject updateBeAssignUserByIdCheckPower(HttpSession session) {
        try {

            boolean result = taskManagerService.updateBeAssignUserByIdCheckPower(session);
            /**
             * 因为指派的权限要求 创建人和负责人 和项目经理角色可以操作 正常逻辑是无法通过
             */
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error();
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
     * 查询任务接口（查询）
     *
     * @param page
     *            当前页
     * @param rows
     *            显示几条
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @GetMapping(value = "/queryAllTask")
    @ApiOperation(value = "查询所有任务列表接口", notes = "查询所有任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Integer", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "rows", paramType = "query", value = "每页有几行", required = true) })
    public JSONObject queryAllTask(Integer page, Integer rows) {
        try {
            PageInfo<Task> result = taskManagerService.queryAllTask(page, rows);
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
            @ApiImplicitParam(dataType = "User", name = "beassignUser.username", paramType = "query", value = "负责人"),
            @ApiImplicitParam(dataType = "Integer", name = "page", paramType = "query", value = "当前页", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "rows", paramType = "query", value = "每页有几行", required = true) })
    public JSONObject queryTaskByTask(@ApiIgnore Task task, Integer page, Integer rows) {
        System.out.println(task);
        try {
            PageInfo<Task> result = taskManagerService.queryTaskByTask(task, page, rows);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

}
