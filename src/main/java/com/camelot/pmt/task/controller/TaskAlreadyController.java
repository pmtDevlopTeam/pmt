package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
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

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/task/taskAlready")
@Api(value = "我的工作台-我的已办-接口", description = "我的工作台-我的已办-接口")
public class TaskAlreadyController {

    @Autowired
    private TaskAlreadyService taskAlreadyService;

    @Autowired
    private TaskRunningService taskRunningService;



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
        ExecuteResult<PageInfo<Map<String, Object>>> result = new ExecuteResult<PageInfo<Map<String, Object>>>();
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
    @RequestMapping(value = "user/queryTaskById", method = RequestMethod.GET)
    public JSONObject queryTaskById(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long id) {
        ExecuteResult<Task> result = new ExecuteResult<Task>();
        try {
            result = taskRunningService.queryTaskById(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }


    /**
     *
     * @Title: updateTaskPendingToRuning
     * @Description: TODO(我的已完成任务转为正在进行)   重做功能
     * @param @param taskId
     * @param @return    设定文件
     * @return JSONObject    返回类型
     * @throws
     */
    @ApiOperation(value = "我的已完成任务转为正在进行、重做功能", notes = "我的已完成任务转为正在进行、重做功能")
    @RequestMapping(value = "/updateTaskAlreadyToRunning", method = RequestMethod.POST)
    public JSONObject updateTaskAlreadyToRunning(
            @ApiParam(name = "id", value = "任务id", required = true) @RequestParam(required = true) Long taskId,
            @ApiParam(name = "delayDescribe", value = "任务描述", required = true) @RequestParam(required = true) String delayDescribe,
            @ApiParam(name = "estimateStartTime", value = "任务预计开始时间", required = true) @RequestParam(required = true) Date estimateStartTime){
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            Long userLoginId = Long.valueOf(1);
            //检查用户是否登录，需要去session中获取用户登录信息
            if(StringUtils.isEmpty(userLoginId)){
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            //更新我的待办任务为正在进行中
            result = taskAlreadyService.updateTaskAlreadyToRunning(taskId, Constant.TaskStatus.RUNING.getValue(),delayDescribe,estimateStartTime);
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
