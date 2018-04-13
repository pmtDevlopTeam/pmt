package com.camelot.pmt.testmanage.casemanage.controller;

import java.util.List;

import com.camelot.pmt.platform.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

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
	    public PageInfo<UseCase> queryUsersByPage(Integer currentPage,Integer pageSize) {
			 PageBean  pageBean=	new  PageBean();
			 pageBean.setCurrentPage(currentPage);
			 pageBean.setPageSize(pageSize);
			 return UseCaseService.selectUseCase(pageBean);
	    }

    @PostMapping
    public void add(HttpServletRequest request, @RequestBody UseCase useCase) {
        try {
            UserModel user = (UserModel) request.getSession().getAttribute("user");
            UseCaseService.add(user, useCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
