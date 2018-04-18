package com.camelot.pmt.project.controller;

import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.service.ProjectBudgetService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lixk
 * @Description: 项目模块-项目预算接口
 * @date 2018年4月17日 下午5:48:37
 */

@RestController
@Api(value = "项目模块-项目预算接口", description = "项目模块-项目预算接口")
@RequestMapping(value = "/project/pojectbudget")
public class ProjectBudgetController {

    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProjectBudgetService projectBudgetService;

    /**
     * 添加项目预算信息
     *@param  ProjectBudget projectBudget 
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "添加项目预算", notes = "添加预算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "actualHours", value = "实际工时", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "budgetaryHours", value = "预计工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "other", value = "其他预算", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String") })
    @PostMapping("/addBudget")
    public JSONObject addBudget(@ApiIgnore ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = projectBudgetService.addBudget(projectBudget);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询项目预算接口(查询)
     *@param  Long projectId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据projectId查询单个项目预算", notes = "查询单个项目预算")
    @GetMapping(value = "/queryBudgetByProjectId")
    public JSONObject queryUserByUserId(@RequestParam(value = "projectId", required = true) Long projectId) {
        ExecuteResult<ProjectBudget> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.queryBudgeByProjectId(projectId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 修改项目预算
     *@param  ProjectBudget projectBudget
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "修改项目预算", notes = "修改项目预算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目预算id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "actualHours", value = "实际工时", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "budgetaryHours", value = "预计工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "other", value = "其他预算", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String") })
    @PostMapping("/updateBudget")
    public JSONObject updateBudget(@ApiIgnore ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            result = projectBudgetService.updateBudget(projectBudget);
            if (result.isSuccess()) {
                return ApiResponse.success();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询项目预算
     *@param  Long projectId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据项目id查询项目预算", notes = "查询单个项目预算")
    @GetMapping("/queryBudget")
    public JSONObject queryBudget(@RequestParam(value = "demandId", required = true) Long projectId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.queryBudget(projectId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     *查询项目结项
     *@param  Long projectId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据项目id查询项目结项", notes = "查询单个项目结项")
    @GetMapping("/queryProjectEnd")
    public JSONObject queryProjectEnd(@RequestParam(value = "demandId", required = true) Long projectId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<>();
        try {
            if ("".equals(projectId)) {
                return ApiResponse.errorPara("项目id为空");
            }
            /**
             * 调用产出物模块service统计发包信息
             */
            result = projectBudgetService.queryBudget(projectId);
            /**
             * 调用产出物service统计文档中心文档类型下文档的个数，携带文档名称；
             */
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询需求变更影响的任务信息
     *@param  Long demandId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的任务", notes = "查询需求变更影响的任务信息")
    @RequestMapping(value = "/queryDemandTaskByDemandId", method = RequestMethod.POST)
    public JSONObject queryDemandTask(@RequestParam(value = "demandId", required = true) Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.queryDemandTaskByDeamdId(demandId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询需求变更影响的任务信息
     *@param  Long demandId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的用例信息", notes = "查询需求变更影响的任务信息")
    @GetMapping(value = "/queryDemandUseCaseByDeamdId")
    public JSONObject queryDemandUseCase(@RequestParam(value = "demandId", required = true) Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.queryDemandUseCaseByDeamdId(demandId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

    /**
     * 查询需求变更影响的bug信息
     *@param  Long demandId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据需求id查询需求变更影响的bug信息", notes = "查询需求变更影响的bug信息")
    @GetMapping(value = "/queryDemandBugByDeamdId")
    public JSONObject queryDemandBug(@RequestParam(value = "demandId",required = true) Long demandId) {
        ExecuteResult<List<Map<String, Object>>> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.findDemandBugByDeamdId(demandId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
        return ApiResponse.error();
    }

}
