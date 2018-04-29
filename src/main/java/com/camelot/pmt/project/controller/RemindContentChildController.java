package com.camelot.pmt.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.project.service.RemindContentChildService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author qiaodj
 * @date 2018年4月28日
 */
@RestController
@RequestMapping(path = "/remind", produces = "application/json;charset=utf-8")
@Api(value = "提醒内容子表接口", description = "提醒内容子表接口")
public class RemindContentChildController {
    private static final Logger logger = LoggerFactory.getLogger(RemindContentChildController.class);

    @Autowired
    private RemindContentChildService remindContentChildService;

    /**
     * 根据项目id和内容主表id查询 延时延期提醒
     * 
     * @param projectId
     * @param contentId
     * @return
     */
    @ApiOperation(value = "根据项目id和内容主表id查询 延时延期提醒", notes = "根据项目id和内容主表id查询 延时延期提醒")
    @GetMapping(value = "/queryByProjectIdAndByContentId")
    public JSONObject queryByProjectIdAndByContentId(//
            @ApiParam(value = "项目id", required = true) @RequestParam Long projectId, //
            @ApiParam(value = "内容主表id", required = true) @RequestParam Long contentId) {

        logger.info("入参封装的数据为：projectId={},contentId={}", projectId, contentId);
        try {
            List<Object> list = remindContentChildService.queryByProjectIdAndByContentId(projectId, contentId);
            if (list.size() > 0) {
                return ApiResponse.success(list);
            }
            return ApiResponse.error("根据项目id和内容主表id查询 延时延期提醒失败");
        } catch (Exception e) {
            return ApiResponse.error("根据项目id和内容主表id查询 延时延期提醒出现异常");
        }
    }
}
