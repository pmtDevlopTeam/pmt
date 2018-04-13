package com.camelot.pmt.testmanage.casemanage.controller;

import com.camelot.pmt.platform.user.model.UserModel;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

	@ApiOperation(value = "新增用例")
	@PostMapping(value = "userCase/add")
	public void add(HttpServletRequest request, @RequestBody @ApiParam(value = "useCase", required = true) UseCase useCase) {
		try {
			UserModel user = (UserModel) request.getSession().getAttribute("user");
			UseCaseService.add(user, useCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "批量新增用例")
	@PostMapping(value = "userCase/addBatch")
	public void addBatch(HttpServletRequest request, @RequestBody @ApiParam(value = "list", required = true) List<UseCase> list) {
		try {
			UserModel user = (UserModel) request.getSession().getAttribute("user");
			UseCaseService.addBatch(user, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	 
	 @ApiOperation(value = "通过id获取用例信息", notes = "通过id获取用例信息")
	    @RequestMapping(value = "userCase/getUseCaseByUseCaseId", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
	    public ActionBean getUseCaseByUseCaseId(Long id) {
		 ActionBean  actionBean  =new ActionBean();
		 	try {
		 		actionBean.setCode(200);
		 		actionBean.setErrorMessage("成功");
		 		actionBean.setResult(true);
		 		actionBean.setResponse(UseCaseService.getUseCaseByUseCaseId(id));
			} catch (Exception e) {
				actionBean.setCode(500);
				actionBean.setErrorMessage(e.getMessage());
				actionBean.setResult(false);
			}
		 	return actionBean;
	    }
	 
	 
	 @ApiOperation(value = "根据id删除用例信息", notes = "根据id删除用例信息")
	    @RequestMapping(value = "userCase/updateUserCaseDelFlag", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
	    public ActionBean updateUserCaseDelFlag(Long id) {
		 ActionBean  actionBean  =new ActionBean();
		 	try {
		 		UseCaseService.updateUserCaseDelFlag(id);
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
