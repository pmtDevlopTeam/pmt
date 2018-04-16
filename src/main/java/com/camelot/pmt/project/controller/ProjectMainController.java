package com.camelot.pmt.project.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "立项管理接口", description = "立项管理接口")
public class ProjectMainController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectMainController.class);
    @Autowired
    private ProjectMainService projectMainService;

    @ApiOperation(notes = "新建项目", value = "根据具有立项权限的用户新建项目")
    @PostMapping("/api/projectMain/createProject")
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
            @ApiImplicitParam(name = "warnType", value = "预警类型", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "warnTime", value = "预警时间", required = false, paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "warnStatus", value = "预警状态", required = false, paramType = "query", dataType = "String") })
    public JSONObject postCreateProject(//
            @ApiIgnore ProjectMain projectMain, //
            @ApiIgnore ProjectOperate projectOperate, //
            @ApiIgnore ProjectBudget projectBudget, //
            @ApiIgnore Warning warning) {

        logger.info("入参封装的数据为：projectMain={},projectOperate={},projectBudget={},projectBudget={}", projectMain,
                projectOperate, projectBudget, projectBudget);
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
    @GetMapping(value = "/api/projectMain/findAllByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int") })
    public JSONObject findAllByPage(@ApiIgnore Pager<?> page) {
        logger.info("入参封装的数据为：page={}", page);
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
    @GetMapping(value = "/api/projectMain/findByProjectStatus")
    public JSONObject findByProjectStatus(
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus) {
        logger.info("入参封装的数据为：projectStatus={}", projectStatus);
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(projectStatus)) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByProjectStatus(projectStatus);
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
     * @param userId
     * @return
     */
    @ApiOperation(value = "按负责人id查询", notes = "按负责人id查询")
    @GetMapping(value = "/api/projectMain/findByUserId")
    public JSONObject findByUserId(@ApiParam(value = "负责人Id", required = true) @RequestParam String userId) {
        logger.info("入参封装的数据为：userId={}", userId);
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(userId)) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByUserId(userId);
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
     * @param createUserId
     * @return
     */
    @ApiOperation(value = "按创建人id查询", notes = "按创建人id查询")
    @GetMapping(value = "/api/projectMain/findByCreateUserId")
    public JSONObject findByCreateUserId(
            @ApiParam(value = "创建人id", required = true) @RequestParam String createUserId) {
        logger.info("入参封装的数据为：createUserId={}", createUserId);
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(createUserId)) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByCreateUserId(createUserId);
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
     * @param modifyUserId
     * @return
     */
    @ApiOperation(value = "按修改人id查询", notes = "按修改人id查询")
    @GetMapping(value = "/api/projectMain/findByModifyUserId")
    public JSONObject findByModifyUserId(
            @ApiParam(value = "修改人id", required = true) @RequestParam String modifyUserId) {
        logger.info("入参封装的数据为：modifyUserId={}", modifyUserId);
        ExecuteResult<List<ProjectMain>> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(modifyUserId)) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.findByModifyUserId(modifyUserId);
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
     * @param id
     * @param createUserId
     * @param userId
     * @param modifyUserId
     * @param projectNum
     * @param projectName
     * @param projectStatus
     * @param startTime
     * @param endTime
     * @param projectDesc
     * @param operateDesc
     * @return
     */
    @ApiOperation(value = "按主键id更新数据", notes = "按主键id更新数据")
    @PutMapping(value = "/api/projectMain/updateByPrimaryKeySelective")
    public JSONObject updateByPrimaryKeySelective(//
            @ApiParam(value = "id", required = true) @RequestParam Long id, //
            @ApiParam(value = "创建人id", required = true) @RequestParam String createUserId, //
            @ApiParam(value = "负责人Id", required = true) @RequestParam String userId, //
            @ApiParam(value = "修改人id", required = true) @RequestParam String modifyUserId, //
            @ApiParam(value = "项目编号", required = true) @RequestParam String projectNum, //
            @ApiParam(value = "项目名称", required = true) @RequestParam String projectName, //
            @ApiParam(value = "项目状态", required = true) @RequestParam String projectStatus, //
            @ApiParam(value = "开始时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date startTime, //
            @ApiParam(value = "结束时间", required = true) @RequestParam @DateTimeFormat(iso = ISO.DATE) Date endTime, //
            @ApiParam(value = "项目描述", required = true) @RequestParam String projectDesc, //
            @ApiParam(value = "操作描述", required = true) @RequestParam String operateDesc) {

        logger.info(
                "入参封装的数据为：id={},createUserId={},userId={},modifyUserId={},projectNum={},projectName={},projectStatus={},startTime={},endTime={},projectDesc={},operateDesc={}",
                id, createUserId, userId, modifyUserId, projectNum, projectName, projectStatus, startTime, endTime,
                projectDesc, operateDesc);
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (id == null || StringUtils.isEmpty(createUserId) || StringUtils.isEmpty(userId)
                    || StringUtils.isEmpty(modifyUserId) || StringUtils.isEmpty(projectNum)
                    || StringUtils.isEmpty(projectName) || StringUtils.isEmpty(projectStatus) || startTime == null
                    || endTime == null || StringUtils.isEmpty(projectDesc) || StringUtils.isEmpty(operateDesc)) {
                return ApiResponse.errorPara();
            }

            ProjectMain projectMain = new ProjectMain();
            projectMain.setId(id);
            projectMain.setUserId(userId);
            projectMain.setModifyUserId(modifyUserId);
            projectMain.setModifyTime(new Date());
            projectMain.setProjectNum(projectNum);
            projectMain.setProjectName(projectName);
            projectMain.setProjectStatus(projectStatus);
            projectMain.setProjectDesc(projectDesc);
            projectMain.setStartTime(startTime);
            projectMain.setEndTime(endTime);
            result = projectMainService.updateByPrimaryKeySelective(projectMain, createUserId, operateDesc);
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
     * @param id
     * @param createUserId
     * @param operateDesc
     * @return
     */
    @ApiOperation(value = "根据id删除项目", notes = "根据id删除项目")
    @DeleteMapping(value = "/api/projectMain/deleteByPrimaryKey")
    public JSONObject deleteByPrimaryKey(//
            @ApiParam(value = "id", required = true) @RequestParam Long id, //
            @ApiParam(value = "创建人id", required = true) @RequestParam String createUserId, //
            @ApiParam(value = "operateDesc", required = true) @RequestParam String operateDesc) {

        logger.info("入参封装的数据为：id={},createUserId={},operateDesc={}", id, createUserId, operateDesc);
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(createUserId) || id == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.deleteByPrimaryKey(id, createUserId, operateDesc);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    /**
     * 按项目id查询
     * 
     * @param createUserId
     * @return
     */
    @ApiOperation(value = "按项目id查询", notes = "按项目id查询")
    @GetMapping(value = "/api/projectMain/selectByPrimaryKey")
    public JSONObject selectByPrimaryKey(@ApiParam(value = "项目id", required = true) @RequestParam Long id) {

        logger.info("入参封装的数据为：id={}", id);
        ExecuteResult<ProjectMain> result = new ExecuteResult<>();
        try {
            if (id == null) {
                return ApiResponse.errorPara();
            }
            result = projectMainService.selectByPrimaryKey(id);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }

    // 根据项目id更新项目状态 项目成员表成员状态 需求状态 任务状态 bug用例状态

    // 项目id 修改人 修改时间 项目状态 projectMain

    //项目操作表存数据
    
    //需求表更改状态
    
    //任务表更改状态
    
    //bug用例状态
}
