package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskLogService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 日志管理接口
 * 
 * @ClassName: TaskLogController
 * @Description: TODO
 * @author jh
 * @date 2018年4月16日
 *
 */
@RestController
@RequestMapping("/tasklog")
public class TaskLogController {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskLogService taskLogService;

    @ApiOperation(value = "添加任务历史记录", notes = "添加任务历史记录")
    @RequestMapping(value = "/insert-tasklog", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, paramType = "form", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "操作人ID", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "operationTime", value = "操作时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "operationButton", value = "操作功能", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "operationDescribe", value = "操作描述", required = true, paramType = "form", dataType = "String") })
    public JSONObject insertTaskLog(@ApiIgnore TaskLog tasklog) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
        	User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }else if (tasklog == null) {
                return ApiResponse.errorPara();
            }
            // 调用接口进行更新
            result = taskLogService.insertTaskLog(tasklog);
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     *
     * @Title: queryUserAll @Description: TODO查询任务历史记录 @param @return @return
     *         JSONObject @throws myp
     */
    @ApiOperation(value = "查询任务历史记录", notes = "查询任务历史记录")
    @RequestMapping(value = "/queryTaskLogList", method = RequestMethod.POST)
    public JSONObject queryTaskLogList(
            @ApiParam(name = "id", value = "任务id", required = false) @RequestParam(required = false) Long id,
			@ApiParam(name = "page", value = "页码", required = true) @RequestParam(required = true) Integer page,
			@ApiParam(name = "rows", value = "每页数量", required = true) @RequestParam(required = true) Integer rows) {
        try {
        	User user = (User) ShiroUtils.getSessionAttribute("user");
            // 检查用户是否登录，需要去session中获取用户登录信息
            if (user == null) {
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
        	PageInfo<TaskLog> taskLogList = taskLogService.queryTaskLogList(id,page,rows);
            return ApiResponse.success(taskLogList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

}
