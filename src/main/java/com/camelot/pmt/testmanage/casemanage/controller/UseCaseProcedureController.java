package com.camelot.pmt.testmanage.casemanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	    public ActionBean updateUserCaseDelFlag(Long id) {
		 ActionBean  actionBean  =new ActionBean();
		 	try {
		 		useCaseProcedureService.deleteByPrimaryKey(id);
		 		actionBean.setCode(200);
		 		actionBean.setErrorMessage("成功");
		 		actionBean.setResult(true);
			} catch (Exception e) {
				actionBean.setCode(500);
				actionBean.setErrorMessage(e.getMessage());
				actionBean.setResult(false);
			}
		 	return actionBean;
	    }
}
