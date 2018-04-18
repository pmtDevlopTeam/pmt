package com.camelot.pmt.project.controller;

import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
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
        try {
            if(null == projectBudget){
                return ApiResponse.errorPara("请求参数异常");
            }
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                return ApiResponse.jsonData(APIStatus.NOT_USERNAME_404);
            }
            projectBudget.setCreateUserId(user.getUserId());
            projectBudget.setModifyUserId(user.getUserId());
            boolean flag = projectBudgetService.addBudget(projectBudget);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
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
        try {
            if(null == projectBudget){
                return ApiResponse.errorPara("请求参数异常");
            }
            User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                return ApiResponse.jsonData(APIStatus.NOT_USERNAME_404);
            }
            projectBudget.setModifyUserId(user.getUserId());
            boolean flag = projectBudgetService.updateBudget(projectBudget);
            if (flag) {
                return ApiResponse.success();
            }
            return ApiResponse.error("修改异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
    }

    /**
     * 查询项目预算接口(get)
     *@param  Long projectId
     *@return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
    @ApiOperation(value = "根据projectId查询单个项目预算", notes = "查询单个项目预算")
    @GetMapping(value = "/queryBudgetByProjectId")
    public JSONObject queryUserByUserId(@RequestParam(value = "projectId", required = true) Long projectId) {
        try {
            if((null == projectId) || (0 == projectId)){
                return ApiResponse.errorPara("请求参数异常");
            }
            ProjectBudget projectBudget = projectBudgetService.queryBudgeByProjectId(projectId);
            return ApiResponse.success(projectBudget);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
        }
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
    @GetMapping("/queryProjectEndById")
    public JSONObject queryProjectEnd(@RequestParam(value = "projectId", required = true) Long projectId) {
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

}
