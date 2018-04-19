package com.camelot.pmt.caserepertory.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /*
     * 用例库查询
     */
    @ApiOperation(value = "分页获取用例库列表", notes = "分页获取用例库列表")
    @RequestMapping(value = "/selectCondition", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "caseType", value = "类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "caseTitle", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "applyPhase", value = "试用阶段", required = true, paramType = "query", dataType = "String") })
    public JSONObject queryCaseRepertoryByPage(Integer currentPage, Integer pageSize, String caseType, String caseTitle,
            String applyPhase) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageBean", pageBean);
        if (caseType != null) {
            map.put("caseType", caseType);
        }
        if (caseTitle != null) {
            map.put("caseTitle", caseTitle);
        }
        if (caseType != null) {
            map.put("applyPhase", applyPhase);
        }

        ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
        try {
            // 调用查询bug分页接口
            result = caseRepertoryService.selectCondition(map);
            if (result.getErrorMessages().size() != 0) {
                return ApiResponse.error(result.getErrorMessage());
            }
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /*
     * 功能： 批量选择测试用例导入到用例库（这是不可以编辑的）单条导入可以用 用例复制功能的查询接口 并将回显到新增用例库用例的页面上进行编辑
     * 描述：把测试用例导入到用例库中 作者：sll
     */
    @ApiOperation(value = "导入用例库", notes = "导入用例库")
    @RequestMapping(value = "/addCaseRepertoryByCaseid", method = RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "用例IDS", required = true, paramType = "query", dataType = "String")
    public JSONObject addCaseRepertoryByCaseid(String ids) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 调用添加bug接口
            result = caseRepertoryService.addCaseRepertoryByCaseid(ids);
            if (result.getErrorMessages().size() != 0) {
                return ApiResponse.error(result.getErrorMessage());
            }
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

    /*
     * 功能： 批量选择测试用例库导入到用例表中（这是不可以编辑的）单条导入可以用 用例复制功能的查询接口 并将回显到新增用例库用例的页面上进行编辑
     * 描述：把测试用例库中的数据导入到用例 作者：sll
     */
    @ApiOperation(value = "导入用例", notes = "导入用例")
    @RequestMapping(value = "/addUserCaseByCaseRepertoryid", method = RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "用例IDS", required = true, paramType = "query", dataType = "String")
    public JSONObject addUserCaseByCaseRepertoryid(String ids) {

        ExecuteResult<String> result = new ExecuteResult<String>();
        try {
            // 调用添加bug接口
            result = caseRepertoryService.addUserCaseByCaseRepertoryid(ids);
            if (result.getErrorMessages().size() != 0) {
                return ApiResponse.error(result.getErrorMessage());
            }
            // 成功返回
            return ApiResponse.success(result.getResult());
        } catch (Exception e) {
            // 异常
            return ApiResponse.error();
        }
    }

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
    public ActionBean add(
            @RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
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
    public ActionBean update(
            @RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
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
            @ApiImplicitParam(name = "ids", value = "用例id,多个id用逗号隔开", required = true, paramType = "query", dataType = "String") })
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
