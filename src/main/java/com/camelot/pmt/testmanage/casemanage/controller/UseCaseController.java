package com.camelot.pmt.testmanage.casemanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "用例管理", description = "用例管理接口")
public class UseCaseController {
	
	@Autowired
	UseCaseService UseCaseService;
	
	 @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
	    @RequestMapping(value = "userCase/queryUserCase", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int") })
	    public void queryUsersByPage(@ApiIgnore PageBean<UseCase> pageBean) {
		 
		 	UseCaseService.selectUseCase(pageBean);
		 	
		 	
		 
	    }
	
}
