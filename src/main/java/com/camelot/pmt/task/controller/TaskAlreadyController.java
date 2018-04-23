package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
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
import org.springframework.util.StringUtils;
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


    //日志
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
     * @param Long
     *            id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "我的已完成任务转为正在进行、重做功能", notes = "我的已完成任务转为正在进行、重做功能")
    @RequestMapping(value = "/updateTaskAlreadyToRunning", method = RequestMethod.POST)
    public JSONObject updateTaskAlreadyToRunning(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的任务为关闭
            result = taskAlreadyService.updateTaskAlreadyToRunning(id);
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
     * 我的已办 提测功能 updateTaskToTest
     *
     * @param Long
     *            id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "提测功能", notes = "提测功能")
    @RequestMapping(value = "/updateTaskToTest", method = RequestMethod.POST)
    public JSONObject updateTaskToTest(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的任务为关闭
            result = taskAlreadyService.updateTaskToTest(id);
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
     * 查询所有已完成的任务 queryTaskAlready
     *
     * @param
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询所有已完成的任务", notes = "查询所有已完成的任务")
    @RequestMapping(value = "/queryTaskAlready", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目ID", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "project.projectName", value = "项目名称", required = true, paramType = "query", dataType = "ProjectMain"),

            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "Integer") })
    public JSONObject queryTaskAlready(@ApiIgnore Task task, Integer page, Integer rows) {
        String userLoginId = String.valueOf(1);
        ExecuteResult<PageInfo<Task>> result = new ExecuteResult<PageInfo<Task>>();
        try {
            result = taskAlreadyService.queryTaskAlready(page, rows, "2");
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 查询单个任务明细 queryTaskById
     *
     * @param Long
     *            id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "根据id查询单个任务明细", notes = "根据id查询单个任务明细")
    @RequestMapping(value = "queryTaskById", method = RequestMethod.GET)
    public JSONObject queryTaskById(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        try {
            Map<String, Object> result = taskManagerService.queryTaskById(id);
            return ApiResponse.success(result);
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询任务历史记录 重做页面 queryTaskLogList
     *
     * @param Long
     *            id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "查询任务历史记录-重做页面", notes = "查询任务历史记录-重做页面")
    @RequestMapping(value = "/queryToRedoPage", method = RequestMethod.GET)
    public JSONObject queryToRedoPage(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<List<TaskLog>> result = null;
        try {
            result = taskLogService.queryTaskLogList(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询任务历史记录 提测页面 queryTaskLogList
     *
     * @param Long
     *            id
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */

    @ApiOperation(value = "查询任务历史记录-提测页面", notes = "查询任务历史记录-提测页面")
    @RequestMapping(value = "/queryToTestPage", method = RequestMethod.GET)
    public JSONObject queryToTestPage(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<List<TaskLog>> result = null;
        try {
            result = taskLogService.queryTaskLogList(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }


    /**
     * 查询所有的任务 queryTaskAlready
     *
     * @param
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "查询所有的任务", notes = "查询所有的任务")
    @RequestMapping(value = "/queryMyAllTask", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ProjectMain", name = "project.id", paramType = "query", value = "项目编号"),
            @ApiImplicitParam(dataType = "String", name = "taskName", paramType = "query", value = "任务名称"),
            @ApiImplicitParam(dataType = "String", name = "taskNum", paramType = "query", value = "任务编号"),
            @ApiImplicitParam(dataType = "Demand", name = "demand.id", paramType = "query", value = "需求编号")})
    public JSONObject queryMyAllTask(@ApiIgnore Task task) {
        String userLoginId = String.valueOf(1);
        try {
            // 获取当前登录人
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (null == user) {
                return ApiResponse.jsonData(APIStatus.INVALIDSESSION_LOGINOUTTIME);
            }
            task.setBeassignUser(user);
            Map<String , Object> result = taskAlreadyService.queryMyAllTask(task);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

}
