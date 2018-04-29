package com.camelot.pmt.project.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.project.service.RemindReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author qiaodj
 * @date 2018年4月27日
 */
@RestController
@RequestMapping(path = "/remind", produces = "application/json;charset=utf-8")
@Api(value = "提醒报告接口", description = "提醒报告接口")
public class RemindReportController {
    private static final Logger logger = LoggerFactory.getLogger(RemindReportController.class);

    @Autowired
    private RemindReportService remindReportService;

    /**
     * 根据项目id 角色id 提醒状态增加RemindReport表数据
     * 
     * @param projectId
     * @param projectRoleId
     * @param remindStatus
     * @return
     */
    @ApiOperation(value = "根据项目id 角色id 提醒状态增加RemindReport表数据", notes = "根据项目id 角色id 提醒状态增加RemindReport表数据")
    @PostMapping(value = "/addRemindReport")
    public JSONObject addRemindReport(//
            @ApiParam(value = "项目id", required = true) @RequestParam Long projectId, //
            @ApiParam(value = "提醒角色id", required = true) @RequestParam String projectRoleId, //
            @ApiParam(value = "提醒状态", required = true) @RequestParam String remindStatus) {
        logger.info("入参封装的数据为：projectId={},projectRoleId={},remindStatus={}", projectId, projectRoleId, remindStatus);
        if (projectId == null || StringUtils.isEmpty(projectRoleId) || StringUtils.isEmpty(remindStatus)) {
            return ApiResponse.errorPara("入参不能为空");
        }
        try {
            int remindReportNum = remindReportService.addRemindReport(projectId, projectRoleId, remindStatus);
            logger.debug("调用remindReportService的addRemindReport接口返回的条数为：", remindReportNum);
            if (remindReportNum > 0) {
                return ApiResponse.success("新增数据成功");
            } else {
                return ApiResponse.error("新增数据失败");
            }
        } catch (Exception e) {
            // 异常
            logger.error("调用addRemindReport接口出现异常为：", e);
            return ApiResponse.error("新增数据出现异常");
        }
    }
}
