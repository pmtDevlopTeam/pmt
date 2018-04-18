package com.camelot.pmt.project.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.project.model.ProjectOperate;
import com.camelot.pmt.project.service.ProjectOperateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author qiaodj
 * @date 2018年4月17日
 */
@RestController
@RequestMapping(path = "/project/operate", produces = "application/json;charset=utf-8")
@Api(value = "项目操作接口", description = "项目操作接口")
public class ProjectOperateController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectOperateController.class);
    @Autowired
    private ProjectOperateService projectOperateService;

    /**
     * 按创建人id查询
     * 
     * @param createUserId
     * @return
     */
    @ApiOperation(value = "按创建人id查询", notes = "按创建人id查询")
    @GetMapping(value = "/queryByCreateUserId")
    public JSONObject queryByCreateUserId(
            @ApiParam(value = "创建人id", required = true) @RequestParam String createUserId) {
        logger.info("入参封装的数据为：createUserId={}", createUserId);
        ExecuteResult<List<ProjectOperate>> result = new ExecuteResult<>();
        try {
            if (StringUtils.isEmpty(createUserId)) {
                return ApiResponse.errorPara();
            }
            result = projectOperateService.queryByCreateUserId(createUserId);
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
    @GetMapping(value = "/queryByProjectId")
    public JSONObject queryByProjectId(@ApiParam(value = "项目id", required = true) @RequestParam Long projectId) {

        logger.info("入参封装的数据为：projectId={}", projectId);
        ExecuteResult<List<ProjectOperate>> result = new ExecuteResult<>();
        try {
            if (projectId == null) {
                return ApiResponse.errorPara();
            }
            result = projectOperateService.queryByProjectId(projectId);
            if (result.isSuccess()) {
                return ApiResponse.success(result.getResult());
            }
            return ApiResponse.error();
        } catch (Exception e) {
            return ApiResponse.error();
        }
    }
}
