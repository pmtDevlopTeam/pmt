package com.camelot.pmt.testmanage.casemanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.platform.utils.PageBean;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "用例管理", description = "用例管理接口")
public class UseCaseController {
	
	@Autowired
	UseCaseService UseCaseService;
	
	 @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
	    @RequestMapping(value = "userCase/queryUserCase", method = RequestMethod.GET)
	    @ApiImplicitParams({
	    	 	@ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "int"),
	    	 	@ApiImplicitParam(name = "caseTitle", value = "用例标题", required = false, paramType = "query", dataType = "string"),
	            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "caseStatus", value = "用例状态(0:正常；1:被阻塞；2：研究中)", required = false, paramType = "query", dataType = "string"),
	    	 	})
	    public JSONObject queryUsersByPage(Long projectId,String caseTitle,Integer currentPage,Integer pageSize,String caseStatus) {
			 PageBean  pageBean=	new  PageBean();
			 pageBean.setCurrentPage(currentPage);
			 pageBean.setPageSize(pageSize);
			 Map<String,Object> map=new HashMap<String,Object>();
			 if(projectId!=null){
				 map.put("projectId", projectId);
				 
			 }
			 if(!StringUtils.isEmpty(caseTitle)){
				 map.put("caseTitle", caseTitle);
				 
			 }
			 if(!StringUtils.isEmpty(caseStatus)){
				 map.put("caseStatus", caseStatus);
				 
			 }
			 ExecuteResult<PageInfo> result = new ExecuteResult<PageInfo>();
		        try {
		            //调用添加bug接口
		            result = UseCaseService.selectUseCase(pageBean,map);
		            // 成功返回
		            return ApiResponse.success(result.getResult());
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
	    }

	@ApiOperation(value = "新增用例")
	@PostMapping(value = "userCase/add")
	public ActionBean add(HttpServletRequest request, @RequestBody @ApiParam(value = "useCase", required = true) UseCase useCase) {
		ActionBean actionBean = new ActionBean();
	 	try {
			UserModel user = (UserModel) request.getSession().getAttribute("user");
			UseCaseService.add(user, useCase);
			actionBean.setCode(200);
			actionBean.setResult(true);
		} catch (Exception e) {
			actionBean.setCode(500);
			actionBean.setResult(false);
			actionBean.setErrorMessage(e.getMessage());
		}
		return actionBean;
	}

	@ApiOperation(value = "批量新增用例")
	@PostMapping(value = "userCase/addBatch")
	public ActionBean addBatch(HttpServletRequest request, @RequestBody @ApiParam(value = "list", required = true) List<UseCase> list) {
		ActionBean actionBean = new ActionBean();
	 	try {
			UserModel user = (UserModel) request.getSession().getAttribute("user");
			UseCaseService.addBatch(user, list);
			actionBean.setCode(200);
			actionBean.setResult(true);
		} catch (Exception e) {
			actionBean.setCode(500);
			actionBean.setResult(false);
			actionBean.setErrorMessage(e.getMessage());
		}
		return actionBean;
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
	 
	 
	 @ApiOperation(value = "编辑用例")
		@PostMapping(value = "userCase/edit")
		public ActionBean edit(HttpServletRequest request, @RequestBody @ApiParam(value = "useCase", required = true) UseCase useCase) {
		 ActionBean  actionBean  =new ActionBean();
			try {
				UserModel user = (UserModel) request.getSession().getAttribute("user");
				actionBean.setCode(200);
		 		actionBean.setErrorMessage("成功");
		 		actionBean.setResult(true);
				UseCaseService.edit(user, useCase);
			} catch (Exception e) {
				actionBean.setCode(500);
				actionBean.setErrorMessage(e.getMessage());
				actionBean.setResult(false);
			}
			return actionBean;
		}
	
}
