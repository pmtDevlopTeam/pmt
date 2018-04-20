package com.camelot.pmt.task.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.task.model.TaskFile;
import com.camelot.pmt.task.service.TaskFileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * 附件相关接口
 *
 * @author zlh
 * @date 2018/4/20 10:01
 */
@RestController
@RequestMapping("/task/taskFile")
public class TaskFileController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskFileService taskFileService;

    @GetMapping("/download")
    @ApiOperation(value = "附件下载接口", notes = "附件下载接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "attachmentUrl", paramType = "query", value = "附件地址url", required = true),
            @ApiImplicitParam(dataType = "String", name = "attachmentTile", paramType = "query", value = "附件名称", required = true)
    })
    public JSONObject download(@ApiIgnore TaskFile taskFile, HttpServletResponse response) {
        try {
            System.out.println(taskFile.getAttachmentTile()+taskFile.getAttachmentUrl());
            boolean result = taskFileService.download(taskFile, response);
            if (result) {
                return ApiResponse.success();
            }
            return ApiResponse.error("下载异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}
