package com.camelot.pmt.caserepertory.controller;

import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description
 *
 * @author Yurnero
 */
@Api(description = "用例库接口")
@RestController
@RequestMapping(value = "/case_repertory")
public class CaseRepertoryController2 {

    @Autowired
    private CaseRepertoryService caseRepertoryService;

    @ApiOperation(value = "根据ID查询用例")
    @GetMapping(value = "{id}")
    public ActionBean getById(@PathVariable Long id) {
        ActionBean actionBean = new ActionBean();
        try {
            actionBean.setCode(200);
            actionBean.setResponse(caseRepertoryService.getById(id));
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }

    @ApiOperation(value = "新增用例")
    @PostMapping
    public ActionBean add(@RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
        ActionBean actionBean = new ActionBean();
        try {
            caseRepertoryService.add(caseRepertory);
            actionBean.setCode(200);
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }

    @ApiOperation(value = "批量新增")
    @PostMapping(value = "/add_batch")
    public ActionBean addBatch(@RequestBody @ApiParam(value = "list", required = true) List<CaseRepertory> list) {
        ActionBean actionBean = new ActionBean();
        try {
            caseRepertoryService.addBatch(list);
            actionBean.setCode(200);
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }

    @ApiOperation(value = "更新用例")
    @PutMapping
    public ActionBean update(@RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
        ActionBean actionBean = new ActionBean();
        try {
            caseRepertoryService.update(caseRepertory);
            actionBean.setCode(200);
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }

    @ApiOperation(value = "批量删除用例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "用例id,多个id用逗号隔开", required = true, paramType = "query", dataType = "String")
    })
    @DeleteMapping
    public ActionBean remove(String ids) {
        ActionBean actionBean = new ActionBean();
        try {
            caseRepertoryService.remove(ids);
            actionBean.setCode(200);
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }
}
