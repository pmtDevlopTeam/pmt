package com.camelot.pmt.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
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
            @ApiImplicitParam(name = "userId", value = "负责人id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "projectNum", value = "项目编号", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "projectDesc", value = "项目描述", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "operateDesc", value = "操作描述", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "budgetaryHours", value = "预计工时", required = true, paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "warnType", value = "预警类型", required = true, paramType = "form", dataType = "String"),
            @ApiImplicitParam(name = "warnTime", value = "预警时间", required = true, paramType = "form", dataType = "Date"),
            @ApiImplicitParam(name = "warnStatus", value = "预警状态", required = true, paramType = "form", dataType = "String") })
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
}
