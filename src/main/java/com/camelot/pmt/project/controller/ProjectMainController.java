package com.camelot.pmt.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.Pager;
import com.camelot.pmt.project.model.ProjectBudget;
import com.camelot.pmt.project.model.ProjectMain;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.model.Warning;
import com.camelot.pmt.project.service.ProjectMainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "立项管理接口", description = "立项管理接口")
public class ProjectMainController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectMainController.class);
    @Autowired
    private ProjectMainService projectMainService;

    @ApiOperation(notes = "新建项目", value = "根据具有立项权限的用户新建项目")
    @PostMapping("/api/project/createProject")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "负责人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectNum", value = "项目编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectStatus", value = "项目状态", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "projectDesc", value = "项目描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operateDesc", value = "操作描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "budgetaryHours", value = "预计工时", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "warnType", value = "预警类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "warnTime", value = "预警时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "warnStatus", value = "预警状态", required = true, paramType = "query", dataType = "String") })
    public JSONObject postCreateProject(//
            @ApiIgnore ProjectMain projectMain, //
            @ApiIgnore ProjectOperate projectOperate, //
            @ApiIgnore ProjectBudget projectBudget, //
            @ApiIgnore Warning warning) {
        try {
            // 判断非空
            if (projectMain == null) {
                logger.debug("实体projectMain为空");
                return ApiResponse.errorPara();
            }
            if (projectOperate == null) {
                logger.debug("实体projectOperate为空");
                return ApiResponse.errorPara();
            }
            if (projectBudget == null) {
                logger.debug("实体projectBudget为空");
                return ApiResponse.errorPara();
            }
            if (warning == null) {
                logger.debug("实体warning为空");
                return ApiResponse.errorPara();
            }
            // 调用save方法保存数据
            ExecuteResult<String> projectMainResult = projectMainService.save(projectMain, projectOperate,
                    projectBudget, warning);
            logger.debug("调用projectMainService的save接口返回的数据为：", projectMainResult);
            return ApiResponse.success(projectMainResult.getResult());
        } catch (Exception e) {
            // 异常
            logger.error("调用save接口出现异常为：", e);
            return ApiResponse.error();
        }
    }

    /**
     * 分页查询
     * 
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping(value = "/api/project/findAllByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int") })
    public JSONObject findAllByPage(@ApiIgnore Pager<?> page) {
        ExecuteResult<DataGrid<ProjectMain>> result = new ExecuteResult<DataGrid<ProjectMain>>();
        try {
            if (page == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findAllByPage(page);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按状态分类查询
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "按状态分类查询", notes = "按状态分类查询")
    @GetMapping(value = "/api/project/findByProjectStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectStatus", value = "项目状态", required = true, paramType = "query", dataType = "String"), })
    public JSONObject findByProjectStatus(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByProjectStatus(projectMain.getProjectStatus());
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按负责人id查询
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "按负责人id查询", notes = "按负责人id查询")
    @GetMapping(value = "/api/project/findByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "负责人Id", required = true, paramType = "query", dataType = "String"), })
    public JSONObject findByUserId(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByUserId(projectMain.getUserId());
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按创建人id查询
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "按创建人id查询", notes = "按创建人id查询")
    @GetMapping(value = "/api/project/findByCreateUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = true, paramType = "query", dataType = "String"), })
    public JSONObject findByCreateUserId(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByCreateUserId(projectMain.getCreateUserId());
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按修改人id查询
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "按修改人id查询", notes = "按修改人id查询")
    @GetMapping(value = "/api/project/findByModifyUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String"), })
    public JSONObject findByModifyUserId(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByModifyUserId(projectMain.getModifyUserId());
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按主键id更新数据
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "按主键id更新数据", notes = "按主键id更新数据")
    @PutMapping(value = "/api/project/updateByPrimaryKeySelective")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "负责人Id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectNum", value = "项目编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "projectStatus", value = "项目状态", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "projectDesc", value = "项目描述", required = true, paramType = "query", dataType = "String") })
    public JSONObject updateByPrimaryKeySelective(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.updateByPrimaryKeySelective(projectMain);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 根据id删除项目
     * 
     * @param projectMain
     * @return
     */
    @ApiOperation(value = "根据id删除项目", notes = "根据id删除项目")
    @DeleteMapping(value = "/api/project/deleteByPrimaryKey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "Long") })
    public JSONObject deleteByPrimaryKey(@ApiIgnore ProjectMain projectMain) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (projectMain == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.deleteByPrimaryKey(projectMain.getId());
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

}
