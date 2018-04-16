package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.*;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.task.model.Task;
import com.camelot.pmt.task.service.TaskAlreadyService;
import com.camelot.pmt.task.service.TaskRunningService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
    @RequestMapping(value = "/queryoverdueTaskAlready", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int")})
    public JSONObject queryoverdueTaskAlready(@ApiIgnore Pager page) {
        ExecuteResult<DataGrid<Map<String, Object>>> result = new ExecuteResult<DataGrid<Map<String, Object>>>();
        try {
            Long userLoginId = Long.valueOf(1);
            //检查用户是否登录，需要去session中获取用户登录信息
            if(StringUtils.isEmpty(userLoginId)){
                return ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            if (page == null) {
                return ApiResponse.errorPara();
            }
            result = taskAlreadyService.queryoverdueTaskAlready(page,userLoginId);
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
    @RequestMapping(value = "user/queryTaskById", method = RequestMethod.POST)
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


}
