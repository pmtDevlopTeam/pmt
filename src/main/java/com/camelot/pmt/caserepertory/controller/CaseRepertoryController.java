package com.camelot.pmt.caserepertory.controller;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.caserepertory.model.CaseRepertory;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.caserepertory.PageBean;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.menu;

/**
 * 用例库接口
 *
 * @author Yurnero
 */
@RestController
@Api(value = "系统管理-用例库接口", description = "系统管理-用例库接口")
@RequestMapping(value = "/caserepertory")
public class CaseRepertoryController {
    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CaseRepertoryService caseRepertoryService;


    /*
     *功能： 批量选择测试用例导入到用例库（这是不可以编辑的）单条导入可以用 用例复制功能的查询接口  并将回显到新增用例库用例的页面上进行编辑
     * 描述：把测试用例导入到用例库中
     * 作者：sll
     */
    @ApiOperation(value = "导入用例库", notes = "批量导入用例库")
    @RequestMapping(value = "/addCaseRepertoryByCaseid", method = RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "用例IDS", required = true, paramType = "query", dataType = "String")
    public JSONObject addCaseRepertoryByCaseid(String ids) {
        try {
            boolean flag = false;
            flag = caseRepertoryService.addCaseRepertoryByCaseid(ids);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }

    }

    /*
     *功能： 批量选择测试用例库导入到用例表中（这是不可以编辑的）单条导入可以用 用例复制功能的查询接口  并将回显到新增用例库用例的页面上进行编辑
     * 描述：把测试用例库中的数据导入到用例
     * 作者：sll
     */
    @ApiOperation(value = "导入用例", notes = "批量导入用例")
    @RequestMapping(value = "/addUserCaseByCaseRepertoryid", method = RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "用例IDS", required = true, paramType = "query", dataType = "String")
    public JSONObject addUserCaseByCaseRepertoryid(String ids) {
        try {
            boolean flag = false;
            flag = caseRepertoryService.addUserCaseByCaseRepertoryid(ids);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 新增用例库
     * @param caseRepertory 用例库信息
     */
    @ApiOperation(value = "新增用例库用例",notes = "新增单条用例到用例库中")
    @PostMapping
    @RequestMapping(value = "/addCaseRepertory", method = RequestMethod.POST)
    public JSONObject addCaseRepertory(@RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
        try {
            boolean flag = false;
            flag =caseRepertoryService.addCaseRepertory(caseRepertory);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 批量新增用例库
     * @param list 用例库集合

    @ApiOperation(value = "批量新增",notes = "新增多条用例到用例库中")
    @PostMapping(value = "/add_batch")
    public JSONObject addBatchCaseRepertory(@RequestBody @ApiParam(value = "list", required = true) List<CaseRepertory> list) {
        try {
            boolean flag = false;
            flag = caseRepertoryService.addBatchCaseRepertory(list);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("添加异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
     */

    /**
     * 根据用例库ID,批量删除用例库
     *
     * @param ids 用例id,多个id用逗号隔开
     */
    @ApiOperation(value = "批量删除用例",notes = "删除多条用例到用例库中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "用例id,多个id用逗号隔开", required = true, paramType = "query", dataType = "String")
    })
    @DeleteMapping
    @RequestMapping(value = "/deleteCaseRepertory", method = RequestMethod.GET)
    public JSONObject deleteCaseRepertory(String ids) {
        try {
            boolean flag = false;
            flag = caseRepertoryService.deleteCaseRepertory(ids);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("删除异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 更新用例库信息
     * @param caseRepertory 用例库信息
     */
    @ApiOperation(value = "更新用例",notes = "更新用例到用例库中")
    @PutMapping
    @RequestMapping(value = "/updateCaseRepertory", method = RequestMethod.POST)
    public JSONObject updateCaseRepertory(@RequestBody @ApiParam(value = "caseRepertory", required = true) CaseRepertory caseRepertory) {
        try {
            boolean flag = false;
            flag = caseRepertoryService.updateCaseRepertory(caseRepertory);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("更新异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /**
     * 根据ID查询用例库信息
     *
     * @param id 用例库ID
     */
    @ApiOperation(value = "根据ID查询用例")
    @GetMapping(value = "{id}")
    @RequestMapping(value = "/queryCaseRepertoryById", method = RequestMethod.GET)
    public JSONObject queryCaseRepertoryById(@PathVariable Long id) {
        try {
            CaseRepertory  caseRepertory=caseRepertoryService.queryCaseRepertoryById(id);
            return ApiResponse.success(caseRepertory);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }

    /*
      * 用例库查询
	  */
    @ApiOperation(value = "分页获取用例库列表", notes = "分页获取用例库列表")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "caseType", value = "类型", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "caseTitle", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "applyPhase", value = "试用阶段", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/selectCondition", method = RequestMethod.GET)
    public JSONObject queryCaseRepertoryByPage(@ApiIgnore CaseRepertory caseRepertory,@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "1") Integer currentPage) {
        try {
            List<CaseRepertory> list = caseRepertoryService.queryCaseRepertoryByPage(caseRepertory, pageSize,currentPage);
            PageInfo<CaseRepertory> result = new PageInfo<CaseRepertory>(list);
            return ApiResponse.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
    }
}
