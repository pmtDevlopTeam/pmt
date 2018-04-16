package com.camelot.pmt.caserepertory.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.caserepertory.service.CaseRepertoryService;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;
import com.github.pagehelper.PageInfo;

/**
 * 用例库管理
 * 
 * @author maple
 */
@RestController
@Api(value = "用例库管理", description = "用例库管理")
public class CaseRepertoryController {
	@Autowired
	private CaseRepertoryService caseRepertoryService;
	
	 @ApiOperation(value = "分页获取bug列表", notes = "分页获取bug列表")
	    @RequestMapping(value = "bug/selectCondition", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int")
	    })
	    public JSONObject queryCaseRepertoryByPage(Integer currentPage,Integer pageSize) {
			 PageBean  pageBean=	new  PageBean();
			 pageBean.setCurrentPage(currentPage);
			 pageBean.setPageSize(pageSize);
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("pageBean", pageBean);
			 //用户id
			
			 
			 ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
		        try {
		            //调用查询bug分页接口
		            result = caseRepertoryService.selectCondition(map);
		            if(result.getErrorMessages().size()!=0){
		            	return ApiResponse.error(result.getErrorMessage());
		            }
		            // 成功返回
		            return ApiResponse.success(result.getResult());
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
	    }
}
