package com.camelot.pmt.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.service.ProjectBudgetService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ClassName: ProjectBudgetController
 * @Description: 项目预算controller
 * @author lixiaokang
 * @date 2018年4月13日 上午10:24:12
 */
@RestController
@RequestMapping("/projectBudget")
public class ProjectBudgetController {

    @Autowired
    private ProjectBudgetService projectBudgetService;

    /**
     * 添加项目预算
     */
    @ApiOperation(value = "添加项目预算", notes = "添加预算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "actual_hours", value = "实际工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "budgetary_hours", value = "预计工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "other", value = "其他预算", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "create_user_id", value = "创建人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modify_user_id", value = "修改人id", required = true, paramType = "query", dataType = "String") })
    @PostMapping("/addProjectBudget")
    public JSONObject addProjectBudget(@ApiIgnore ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 判断非空
            if (projectBudget == null) {
                return ApiResponse.errorPara();
            }
            // 不为空调用接口查询
            result = projectBudgetService.saveProjectBudget(projectBudget);
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     * Description:[查询单个项目预算]
     * 
     * @param projectId
     * @return {"status": {"message": "请求处理成功.","code": 200}, "data":
     *         {projectBudget}]
     */
    @ApiOperation(value = "根据projectId查询单个用户", notes = "查询单个用户")
    @RequestMapping(value = "/queryProBudgetByProjectId", method = RequestMethod.POST)
    public JSONObject queryUserByUserId(
            @ApiParam(name = "projectId", value = "projectId项目预算id", required = true) @RequestParam(required = true) Long projectId) {
        ExecuteResult<ProjectBudget> result = new ExecuteResult<>();
        try {
            result = projectBudgetService.findProjectBudgeByProjectId(projectId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 编辑项目预算
     */
    @ApiOperation(value = "添加项目预算", notes = "添加预算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "项目预算id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "project_id", value = "项目id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "actual_hours", value = "实际工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "budgetary_hours", value = "预计工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "other", value = "其他预算", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "create_user_id", value = "创建人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modify_user_id", value = "修改人id", required = true, paramType = "query", dataType = "String") })
    @PostMapping("/modifyProjectBudget")
    public JSONObject editProjectBudget(@ApiIgnore ProjectBudget projectBudget) {
        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 判断非空
            if (projectBudget == null) {
                return ApiResponse.errorPara();
            }
            // 不为空调用接口查询
            result = projectBudgetService.modifyProjectBudget(projectBudget);
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /**
     * 统计项目预算
     */
    @ApiOperation(value = "根据项目id查询项目预算", notes = "查询单个项目预算")
    @GetMapping("/count")
    public JSONObject countProjectBudget(
            @ApiParam(name = "proId", value = "项目proId", required = true) @RequestParam(required = true) Long proId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<>();
        System.err.println("根据项目id查询项目预算");
        try {
            if ("".equals(proId)) {
                return ApiResponse.errorPara("项目id为空");
            }
            result = projectBudgetService.findBudgetByProId(proId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }

    /**
     * 项目结项计算
     */
    @ApiOperation(value = "根据项目id查询项目结项", notes = "查询单个项目结项")
    @GetMapping("/projectEnd")
    public JSONObject projectEnd(
            @ApiParam(name = "proId", value = "项目proId", required = true) @RequestParam(required = true) Long proId) {
        ExecuteResult<Map<String, Object>> result = new ExecuteResult<>();
        System.err.println("根据项目id查询所有已发布jar包");
        try {
            if ("".equals(proId)) {
                return ApiResponse.errorPara("项目id为空");
            }
            /**
             * 调用版本模块service统计系统发包数据
             */
            result = projectBudgetService.findBudgetByProId(proId);
            /**
             * 调用产出物service统计文档中心文档类型下文档的个数，携带文档名称；
             */
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error();
        }
    }
}
