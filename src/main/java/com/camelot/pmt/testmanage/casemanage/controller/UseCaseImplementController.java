package com.camelot.pmt.testmanage.casemanage.controller;

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

    @ApiOperation(value = "新增执行信息")
    @PostMapping
    public ActionBean add(HttpServletRequest request, @RequestBody @ApiParam(value = "useCaseImplement", required = true) UseCaseImplement useCaseImplement) {
        ActionBean actionBean = new ActionBean();
        try {
            User user = (User) request.getSession().getAttribute("user");
            useCaseImplementService.add(user, useCaseImplement);
            actionBean.setCode(200);
            actionBean.setResult(true);
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }

    @ApiOperation(value = "根据测试用例ID查询执行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useCaseId", value = "测试用例ID", required = true, paramType = "query", dataType = "Long")
    })
    @GetMapping
    public ActionBean findByUseCaseId(Long useCaseId) {
        ActionBean actionBean = new ActionBean();
        try {
            actionBean.setCode(200);
            actionBean.setResult(true);
            actionBean.setResponse(useCaseImplementService.findByUseCaseId(useCaseId));
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }
}
