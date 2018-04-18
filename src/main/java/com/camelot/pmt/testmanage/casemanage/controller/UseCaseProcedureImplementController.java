package com.camelot.pmt.testmanage.casemanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camelot.pmt.testmanage.casemanage.service.UseCaseProcedureImplementService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class UseCaseProcedureImplementController {
	
	
	@Autowired
	UseCaseProcedureImplementService useCaseProcedureImplementService;

	@ApiOperation(value = "根据用例执行id查询执行详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "implementId", value = "测试执行id", required = true, paramType = "query", dataType = "Long")
    })
	 @RequestMapping(value = "useCaseProcedure/queryUseCaseProcedureImplementd", method = RequestMethod.GET)
    public ActionBean queryUseCaseProcedureImplementd(Long implementId) {
        ActionBean actionBean = new ActionBean();
        try {
            actionBean.setCode(200);
            actionBean.setResult(true);
            actionBean.setResponse(useCaseProcedureImplementService.queryUseCaseProcedureImplementd(implementId));
        } catch (Exception e) {
            actionBean.setCode(500);
            actionBean.setResult(false);
            actionBean.setErrorMessage(e.getMessage());
        }
        return actionBean;
    }
}
