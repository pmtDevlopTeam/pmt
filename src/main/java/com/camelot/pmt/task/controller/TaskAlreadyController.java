package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.service.TaskLogService;
import com.camelot.pmt.task.service.TaskManagerService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task/taskAlready")
@Api(value = "我的工作台-我的已办-接口", description = "我的工作台-我的已办-接口")
public class TaskAlreadyController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskAlreadyService taskAlreadyService;

    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private TaskLogService taskLogService;

    /**
     * (我的已完成任务转为正在进行) 重做功能 updateTaskAlreadyToRunning
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "我的已完成任务转为正在进行、重做功能", notes = "我的已完成任务转为正在进行、重做功能")
    @RequestMapping(value = "/updateTaskAlreadyToRunning", method = RequestMethod.POST)
    public JSONObject updateTaskAlreadyToRunning(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            // 更新我的任务为关闭
            flag = taskAlreadyService.updateTaskAlreadyToRunning(id);
            // 判断是否成功
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 我的已办 提测功能 updateTaskToTest
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "提测功能", notes = "提测功能")
    @RequestMapping(value = "/updateTaskToTest", method = RequestMethod.POST)
    public JSONObject updateTaskToTest(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id) {
        boolean flag = false;
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            // 更新我的任务为关闭
            flag = taskAlreadyService.updateTaskToTest(id);
            // 判断是否成功
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询单个任务明细 queryTaskById
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @Deprecated
    @ApiOperation(value = "根据id查询单个任务明细", notes = "根据id查询单个任务明细")
    @RequestMapping(value = "queryTaskById", method = RequestMethod.GET)
    public JSONObject queryTaskById(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            Map<String, Object> map = taskManagerService.queryTaskById(id);
            return ApiResponse.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询任务历史记录 重做页面 queryTaskLogList
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @Deprecated
    @ApiOperation(value = "查询任务历史记录-重做页面", notes = "查询任务历史记录-重做页面")
    @RequestMapping(value = "/queryToRedoPage", method = RequestMethod.GET)
    public JSONObject queryToRedoPage(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            List<TaskLog> taskLogList = taskLogService.queryTaskLogList(id);
            return ApiResponse.success(taskLogList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询任务历史记录 提测页面 queryTaskLogList
     *
     * @param id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @Deprecated
    @ApiOperation(value = "查询任务历史记录-提测页面", notes = "查询任务历史记录-提测页面")
    @RequestMapping(value = "/queryToTestPage", method = RequestMethod.GET)
    public JSONObject queryToTestPage(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            List<TaskLog> taskLogList = taskLogService.queryTaskLogList(id);
            return ApiResponse.success(taskLogList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询所有的任务 queryTaskAlready
     *
     * @param task
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @Deprecated
    @ApiOperation(value = "查询我的所有的任务", notes = "查询我的所有的任务")
    @RequestMapping(value = "/queryMyAllTask", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ProjectMain", name = "project.id", paramType = "query", value = "项目编号"),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "query", value = "任务名称"),
            @ApiImplicitParam(dataType = "String", name = "taskNum", paramType = "query", value = "任务编号"),
            @ApiImplicitParam(dataType = "Demand", name = "demand.id", paramType = "query", value = "需求编号") })
    public JSONObject queryMyAllTask(@ApiIgnore Task task) {
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            task.setBeassignUser(user);
            Map<String, Object> map = taskAlreadyService.queryMyAllTask(task);
            return ApiResponse.success(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 查询所有已完成的任务 queryTaskAlready
     *
     * @param
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询我的已完成的任务", notes = "查询我的已完成的任务")
    @RequestMapping(value = "/queryTaskAlready", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ProjectMain", name = "project.id", paramType = "query", value = "项目编号"),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "query", value = "任务名称"),
            @ApiImplicitParam(dataType = "String", name = "taskNum", paramType = "query", value = "任务编号"),
            @ApiImplicitParam(dataType = "Demand", name = "demand.id", paramType = "query", value = "需求编号"),
            @ApiImplicitParam(dataType = "Integer", name = "page", required = true, paramType = "query", value = "页码"),
            @ApiImplicitParam(dataType = "Integer", name = "rows", required = true, paramType = "query", value = "每页数量") })
    public JSONObject queryTaskAlready(@ApiIgnore Task task, Integer page, Integer rows) {
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            task.setBeassignUser(user);
            PageInfo<Task> taskAlreadyList = taskAlreadyService.queryTaskAlready(page, rows, task);
            return ApiResponse.success(taskAlreadyList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.error();
        }
    }

}
