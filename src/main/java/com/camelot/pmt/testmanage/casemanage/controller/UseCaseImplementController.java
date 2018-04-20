package com.camelot.pmt.testmanage.casemanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.model.UseCaseImplement;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseImplementService;

import javax.servlet.http.HttpServletRequest;

import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用例执行Controller
 *
 * @author Yurnero
 */
@Api(description = "用例执行信息接口")
@RestController
@RequestMapping(value = "/test_manage/case_manage/use_case_implement")
public class UseCaseImplementController {

    @Autowired
    private UseCaseImplementService useCaseImplementService;

    /**
     * 新增执行信息
     *
     * @param request
     *            request
     * @param useCaseImplement
     *            用例执行
     */
    @ApiOperation(value = "新增执行信息")
    @PostMapping
    public JSONObject addUseCaseImplement(HttpServletRequest request,
            @RequestBody @ApiParam(value = "useCaseImplement", required = true) UseCaseImplement useCaseImplement) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            useCaseImplementService.addUseCaseImplement(user, useCaseImplement);
            return ApiResponse.success();
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据测试用例ID查询执行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useCaseId", value = "测试用例ID", required = true, paramType = "query", dataType = "Long") })
    @GetMapping
    public JSONObject queryUseCaseImplementByUseCaseId(Long useCaseId) {
        try {
            return ApiResponse.success(useCaseImplementService.queryUseCaseImplementByUseCaseId(useCaseId));
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
