package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskPendingService;
import com.camelot.pmt.task.utils.Constant.TaskStatus;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: TaskPendingController
 * @Description: TODO(任务-我的待办)
 * @author gxl
 * @date 2018年4月9日 下午5:09:40
 *
 */

@RestController
@RequestMapping(value = "/task/taskPending")
@Api(value = "我的工作台-我的待办：管理接口", description = "我的工作台-我的待办：管理接口")
public class TaskPendingController {
    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskPendingService taskPendingService;

    /**
     * 
     * @Title: queryTaskByTaskId @Description: TODO(查询taskId下的所有子节点) @param @param
     * taskId @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @ApiOperation(value = "查询任务详情", notes = "查询任务详情")
    @RequestMapping(value = "/queryTaskNodeById", method = RequestMethod.POST)
    public JSONObject queryTaskNodeById(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) String id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            Map<String, Object> result = taskPendingService.queryTaskNodeById(Long.valueOf(id));
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 
     * @Title: queryMyPendingTaskList @Description: TODO(查询整个任务列表) @param @return
     * 设定文件 @return JSONObject 返回类型 @throws
     */
    @ApiOperation(value = "查询我的待办全部的任务列表", notes = "查询我的待办全部的任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "project.id", value = "项目标识号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignUser.userId", value = "指派人标识号", required = false, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/queryMyPendingTaskList", method = RequestMethod.POST)
    public JSONObject queryMyPendingTaskList(@ApiIgnore Task task) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 设置被指派人+待办限制
            User userL = new User();
            userL.setUserId(user.getUserId());
            task.setBeassignUser(userL);
            task.setStatus(TaskStatus.PENDINHG.getValue());
            List<Task> result = taskPendingService.queryMyPendingTaskList(task);
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 
     * @Title: updateTaskPendingToRuning @Description:
     * TODO(我的待办任务转为正在进行) @param @param taskId @param @return 设定文件 @return
     * JSONObject 返回类型 @throws
     */
    @ApiOperation(value = "我的待办任务转为正在进行", notes = "我的待办任务转为正在进行")
    @RequestMapping(value = "/updateTaskPendingToRunning", method = RequestMethod.POST)
    public JSONObject updateTaskPendingToRunning(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的待办任务为正在进行中
            String result = taskPendingService.updateTaskPendingToStatus(id, TaskStatus.RUNING.getValue());
            // 判断是否成功
            if (result != null) {
                return ApiResponse.jsonData(APIStatus.OK_200, result);
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 我的待办任务转为关闭
     * 
     * @param taskId
     * @param delayDescribe
     * @param estimateStartTime
     * @return
     */
    @ApiOperation(value = "我的待办任务转为关闭", notes = "我的待办任务转为关闭")
    @RequestMapping(value = "/updateTaskPendingToClose", method = RequestMethod.POST)
    public JSONObject updateTaskPendingToClose(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的待办任务为正在进行中
            String result = taskPendingService.updateTaskPendingToStatus(id, TaskStatus.CLOSE.getValue());
            // 判断是否成功
            if (StringUtil.isNotEmpty(result)) {
                return ApiResponse.jsonData(APIStatus.OK_200, result);
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 
     * @Title: queryAllTaskList @Description: TODO(查询整个任务列表) @param @return
     * 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "查询我的全部的任务列表", notes = "查询我的全部的任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "project.id", value = "项目标识号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignUser.userId", value = "指派人标识号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "任务状态", required = false, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/queryAllTaskList", method = RequestMethod.POST)
    public JSONObject queryAllTaskList(@ApiIgnore Task task) {
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 设置被指派人+待办限制
            User userL = new User();
            userL.setUserId(user.getUserId());
            task.setBeassignUser(userL);
            List<Task> result = taskPendingService.queryAllTaskList(task);
            return ApiResponse.jsonData(APIStatus.OK_200, result);
        } catch (Exception e) {
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 我的待办任务转为延期
     * 
     * @param taskId
     * @param delayDescribe
     * @param estimateStartTime
     * @return
     */
    @Deprecated
    @ApiOperation(value = "我的待办任务转为延期", notes = "我的待办任务转为延期")
    @RequestMapping(value = "/updateTaskPendingToDelay", method = RequestMethod.POST)
    public JSONObject updateTaskPendingToDelay(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id,
            @ApiParam(name = "delayDescribe", value = "延期描述", required = false) @RequestParam(required = false) String delayDescribe,
            @ApiParam(name = "estimateStartTime", value = "任务预计开始时间", required = true) @RequestParam(required = true) Date estimateStartTime) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 更新我的待办任务为正在进行中
            result = taskPendingService.updateTaskPendingToDelay(id, TaskStatus.PENDINHG.getValue(), delayDescribe,
                    estimateStartTime);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 
     * @Title: assignTask @Description: TODO(指派任务:更新指派人和被指派人标识号) @param @param
     * assignUserId @param @param beassignUserId @param @return 设定文件 @return
     * JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "指派任务-更新指派人和被指派人标识号", notes = "指派任务-更新指派人和被指派人标识号")
    @RequestMapping(value = "/updateTaskToAssign", method = RequestMethod.POST)
    public JSONObject updateTaskToAssign(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id,
            @ApiParam(name = "assignUserId", value = "指派人标识号", required = true) @RequestParam(required = true) Long assignUserId,
            @ApiParam(name = "beassignUserId", value = "被指派人标识号", required = true) @RequestParam(required = true) Long beassignUserId) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.updateTaskToAssign(id, assignUserId, beassignUserId);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * @Title: updateTaskPending @Description: TODO(修改待办任务) @param @param
     * task @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "修改待办任务", notes = "修改待办任务")
    @RequestMapping(value = "/updateTaskPending", method = RequestMethod.POST)
    public JSONObject updateTaskPending(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id,
            @ApiParam(name = "taskDescribe", value = "任务描述", required = false) @RequestParam(required = true) String taskDescribe,
            MultipartFile file) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 修改任务
            result = taskPendingService.updateTaskPending(id, taskDescribe, file);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: queryTaskListNodeByParentId @Description:
     * TODO(查询taskId下的所有一级子节点) @param @param taskId @param @return 设定文件 @return
     * JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，查询taskId下的所有一级子节点", notes = "勿调用，一期无用接口，查询taskId下的所有一级子节点")
    @RequestMapping(value = "/queryTaskListNodeByParentId", method = RequestMethod.POST)
    public JSONObject queryTaskListNodeByParentId(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.queryTaskListNodeByParentId(id, TaskStatus.PENDINHG.getValue());
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口，查询被指派人的顶级任务名称列表 @Title: queryTopTaskNameList @Description:
     * TODO(查询我的顶级待办任务) @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，查询我的顶级待办任务", notes = "勿调用，一期无用接口，查询我的顶级待办任务")
    @RequestMapping(value = "/queryTopTaskNameList", method = RequestMethod.GET)
    public JSONObject queryTopTaskNameList() {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 查询顶级待办任务
            result = taskPendingService.queryTopTaskNameList(TaskStatus.PENDINHG.getValue(), userLoginId);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: queryTopAllTaskTreeByTaskId @Description:
     * TODO(查询该任务的父级节点以及祖宗节点，不包含本身，接口用来判断层级结构是否可以拆分子任务) @param @param
     * taskId @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，查询该任务的父级节点以及祖宗节点", notes = "勿调用，一期无用接口，查询该任务的父级节点以及祖宗节点，list中包含本身")
    @RequestMapping(value = "/queryTopAllTaskTreeByTaskId", method = RequestMethod.POST)
    public JSONObject queryTopAllTaskTreeByTaskId(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 查询父级任务树
            result = taskPendingService.queryTopAllTaskTreeByTaskId(id, new ArrayList<Task>());
            // 判断是否成功
            if (result.isSuccess()) {
                // 判断是否达到四层结构
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: queryMyTaskListNodeByParentId @Description:
     * TODO(查询taskId下的所有一级子节点) @param @param taskId @param @return 设定文件 @return
     * JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，查询我的待办任务taskId下的所有一级子节点", notes = "勿调用，一期无用接口,查询我的待办任务taskId下的所有一级子节点")
    @RequestMapping(value = "/queryMyTaskListNodeByParentId", method = RequestMethod.POST)
    public JSONObject queryMyTaskListNodeByParentId(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<List<Task>> result = new ExecuteResult<List<Task>>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.queryMyTaskListNodeByParentId(id, TaskStatus.PENDINHG.getValue(), userLoginId);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: addUser @Description: TODO(添加Task对象接口方法) @param @param
     * task @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，添加子任务", notes = "勿调用，一期无用接口，添加子任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskParentId", value = "父级任务标识号", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "project.id", value = "项目标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demand.id", value = "需求标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignUser.userId", value = "指派人标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "beassignUser.userId", value = "负责人标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateStartTime", value = "任务预期开始时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "estimateEndTime", value = "任务预期结束时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskDescribe", value = "任务描述", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "任务状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateHour", value = "任务预计工时", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public JSONObject addTask(@ApiIgnore Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.save(task);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: editUser @Description: TODO(修改Task对象接口方法) @param @param
     * task @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，修改任务单元", notes = "勿调用，一期无用接口，修改任务单元")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskNum", value = "任务编号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskParentId", value = "父级任务标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "project.id", value = "项目标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demand.id", value = "需求标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignUser.userId", value = "指派人标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "beassignUser.userId", value = "负责人标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateStartTime", value = "任务预期开始时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "estimateEndTime", value = "任务预期结束时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskDescribe", value = "任务描述", required = false, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "任务状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateHour", value = "任务预计工时", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/editTask", method = RequestMethod.POST)
    public JSONObject editTask(@ApiIgnore Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.saveOrUpdate(task);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: deleteTask @Description:
     * TODO(根据taskId删除该任务，若删除该任务下的所有子任务请调用deleteTaskTreeById（）方法) @param @param
     * userId @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，删除单个任务单元", notes = "勿调用，一期无用接口，删除单个任务单元")
    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    public JSONObject deleteTask(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.delete(id);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: deletePendingTaskTreeById @Description:
     * TODO(删除待办任务) @param @param taskId @param @return 设定文件 @return JSONObject
     * 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，删除待办任务", notes = "勿调用，一期无用接口，删除待办任务")
    @RequestMapping(value = "/deletePendingTaskTreeById", method = RequestMethod.POST)
    public JSONObject deletePendingTaskTreeById(
            @ApiParam(name = "id", value = "任务标识号", required = true) @RequestParam(required = true) String id) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.deletePendingTaskTreeById(Long.valueOf(id), TaskStatus.PENDINHG.getValue());
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: addUser @Description: TODO(添加或更新Task对象接口方法) @param @param
     * task @param @return 设定文件 @return JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，添加或更新任务单元", notes = "勿调用，一期无用接口，添加或更新任务单元")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "任务标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskParentId", value = "父级任务标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "项目标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "demandId", value = "需求标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignUserId", value = "指派人标识号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "beassignUserId", value = "负责人", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "assignTime", value = "任务指派时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateStartTime", value = "任务预期开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateEndTime", value = "任务预期结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "actualStartTime", value = "任务实际开始时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "actualEndTime", value = "任务实际结束时间", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskSpeed", value = "任务进度", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "任务状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "abnormalStatus", value = "异常状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "estimateHour", value = "任务预计工时", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "consumeHour", value = "任务已消耗工时", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "remainHour", value = "任务剩余工时", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "taskMileage", value = "任务里程", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "warningHour", value = "预警工时", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "warningStatus", value = "预警状态", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "comment", value = "备注", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "filepath", value = "附件路径", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "filename", value = "附件名称", required = true, paramType = "form", dataType = "String") })
    @RequestMapping(value = "/addOrUpdateTask", method = RequestMethod.POST)
    public JSONObject addOrUpdateTask(@ApiIgnore Task task) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            // 对象检查是否为空
            if (task == null) {
                return ApiResponse.jsonData(APIStatus.ERROR_400, result.getResult());
            }
            result = taskPendingService.save(task);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常错误
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 一期无用接口 @Title: queryTaskTreeByTaskId @Description:
     * TODO(查询taskId下的所有子节点) @param @param taskId @param @return 设定文件 @return
     * JSONObject 返回类型 @throws
     */
    @Deprecated
    @ApiOperation(value = "勿调用，一期无用接口，查询taskId下的所有子节点", notes = "勿调用，一期无用接口，查询taskId下的所有子节点")
    @RequestMapping(value = "/queryTaskTreeByTaskId", method = RequestMethod.POST)
    public JSONObject queryTaskTreeByTaskId(
            @ApiParam(name = "taskId", value = "任务标识号", required = true) @RequestParam(required = true) String taskId) {
        ExecuteResult<Task> result = new ExecuteResult<Task>();
        try {
            Long userLoginId = Long.valueOf(1);
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (StringUtils.isEmpty(userLoginId)) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            result = taskPendingService.queryTaskTreeByTaskId(Long.valueOf(taskId), TaskStatus.PENDINHG.getValue(),
                    userLoginId);
            // 判断是否成功
            if (result.isSuccess()) {
                return ApiResponse.jsonData(APIStatus.OK_200, result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 异常
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

}
