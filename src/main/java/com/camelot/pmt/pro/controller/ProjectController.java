package com.camelot.pmt.pro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.camelot.api.model.Response;
import com.camelot.pmt.pro.service.ProjectService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(path = "/api/v1", produces = "application/json;charset=utf-8")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;

    @GetMapping("/getById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询项目信息", notes = "根据Id查询项目信息", tags = { "pmt-Service" })
    public Response getUserById(@ApiParam(value = "用户ID", required = true) @RequestParam Integer proId) {

        logger.info("proId={}", proId);
        return projectService.getProjectInfo(proId);
    }
}
