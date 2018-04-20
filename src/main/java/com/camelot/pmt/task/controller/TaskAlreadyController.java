package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.model.TaskLog;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.service.TaskLogService;
import com.camelot.pmt.task.service.TaskManagerService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/task/taskAlready")
@Api(value = "我的工作台-我的已办-接口", description = "我的工作台-我的已办-接口")
public class TaskAlreadyController {

    @Autowired
    private TaskAlreadyService taskAlreadyService;

    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private TaskLogService taskLogService;



    /**
     *
     * @Title: queryUserAll @Description: TODO查询所有已完成的任务 @param @return @return
     *         JSONObject @throws
     *         myp
     */
    @ApiOperation(value = "查询所有已完成的任务", notes = "查询所有已完成的任务")
    @RequestMapping(value = "/queryTaskAlready", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int")})
    public JSONObject queryTaskAlready(int page , int rows) {
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
     * <p>
     * Description:[查询单个任务明细]
     * </p>
     *
     * @param id
     *            任务id
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {Task}]
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
     *
     * @Title: updateTaskAlreadyToRuning
     * @Description: TODO(我的已完成任务转为正在进行)   重做功能
     * @param @param taskId
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @ApiOperation(value = "我的已完成任务转为正在进行、重做功能", notes = "我的已完成任务转为正在进行、重做功能")
    @RequestMapping(value = "/updateTaskAlreadyToRunning", method = RequestMethod.POST)
    public JSONObject updateTaskAlreadyToRunning(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            //检查用户是否登录，需要去session中获取用户登录信息
            if(StringUtils.isEmpty(userLoginId)){
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //更新我的任务为关闭
            result = taskAlreadyService.updateTaskAlreadyToRunning(id);
            //判断是否成功
            if(result.isSuccess()){
                return ApiResponse.jsonData(APIStatus.OK_200,result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        }catch (Exception e) {
            //异常
            return ApiResponse.jsonData(APIStatus.ERROR_500,e.getMessage());
        }
    }

    /**
     *
     * @Title: queryUserAll @Description: TODO 查询任务历史记录  重做页面 @param @return @return
     *         JSONObject @throws
     *         myp
     */
    @ApiOperation(value = "查询任务历史记录-重做页面", notes = "查询任务历史记录-重做页面")
    @RequestMapping(value = "/queryTaskLogList", method = RequestMethod.GET)
    public JSONObject queryTaskLogList(
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
     *
     * @Title: updateTaskAlreadyToRuning
     * @Description: TODO 我的已办 提测功能
     * @param @param taskId
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @ApiOperation(value = "提测功能", notes = "提测功能")
    @RequestMapping(value = "/updateTaskToTest", method = RequestMethod.POST)
    public JSONObject updateTaskToTest(
            @ApiParam(name = "id", value = "任务ID", required = true) @RequestParam(required = true) Long id){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            //检查用户是否登录，需要去session中获取用户登录信息
            if(StringUtils.isEmpty(userLoginId)){
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //更新我的任务为关闭
            result = taskAlreadyService.updateTaskToTest(id);
            //判断是否成功
            if(result.isSuccess()){
                return ApiResponse.jsonData(APIStatus.OK_200,result.getResult());
            }
            return ApiResponse.jsonData(APIStatus.ERROR_500, result.getResult());
        }catch (Exception e) {
            //异常
            return ApiResponse.jsonData(APIStatus.ERROR_500,e.getMessage());
        }
    }





}
