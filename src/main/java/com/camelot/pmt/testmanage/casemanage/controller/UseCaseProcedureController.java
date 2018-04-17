package com.camelot.pmt.testmanage.casemanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseProcedureService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class UseCaseProcedureController {
	
	@Autowired
	UseCaseProcedureService useCaseProcedureService;
	
	 @ApiOperation(value = "根据步骤id删除步骤信息", notes = "根据id删除步骤信息")
	    @RequestMapping(value = "userCaseProcedure/DelUserCaseProcedure", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "步骤id", required = true, paramType = "query", dataType = "long") })
	    public JSONObject updateUserCaseDelFlag(Long id) {
		 	ExecuteResult<String> result = new ExecuteResult<String>();
		 	try {
	            //调用添加bug接口
	        	result=useCaseProcedureService.deleteByPrimaryKey(id);
	            // 成功返回
	            return ApiResponse.success(result.getResult());
	        } catch (Exception e) {
	            // 异常
	            return ApiResponse.error();
	        }
	    }
}
