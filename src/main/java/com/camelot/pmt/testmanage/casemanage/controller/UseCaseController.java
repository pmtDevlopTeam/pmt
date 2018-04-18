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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * @author 
 * @Description:用例管理接口
 * @date 2018-04-18
 */
@RestController
@Api(value = "用例管理", description = "用例管理接口")
@RequestMapping(value = "/casemanage")
public class UseCaseController {
	
	@Autowired
	UseCaseService UseCaseService;
	
	@ApiOperation(value = "新增用例")
	@PostMapping(value = "userCase/add")
	public ActionBean add(HttpServletRequest request, @RequestBody @ApiParam(value = "useCase", required = true) UseCase useCase) {
		ActionBean actionBean = new ActionBean();
	 	try {
			User user = (User) request.getSession().getAttribute("user");
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
			User user = (User) request.getSession().getAttribute("user");
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
	    @RequestMapping(value = "userCase/queryByUseCaseId", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
	    public JSONObject queryByUseCaseId(Long id) {
		 	 ExecuteResult<UseCase> result = new ExecuteResult<UseCase>();
		        try {
		            //调用添加bug接口
		        	result=UseCaseService.getUseCaseByUseCaseId(id);
		            // 成功返回
		            return ApiResponse.success(result.getResult());
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
	    }
	 
	 
	 @ApiOperation(value = "根据id删除用例信息", notes = "根据id删除用例信息")
	    @RequestMapping(value = "userCase/updateUserCaseDelFlag", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
	    public JSONObject updateUserCaseDelFlag(Long id) {
		 	try {
	            //调用添加bug接口
		 		boolean	result=UseCaseService.updateUserCaseDelFlag(id);
	            // 成功返回
	            return ApiResponse.success(result);
	        } catch (Exception e) {
	            // 异常
	            return ApiResponse.error();
	        }
	    }
	 
	 
	 	@ApiOperation(value = "编辑用例")
		@PostMapping(value = "userCase/edit")
		public JSONObject edit(HttpServletRequest request, @RequestBody @ApiParam(value = "useCase", required = true) UseCase useCase) {
			ExecuteResult<String> result = new ExecuteResult<String>();
		 	try {
	            //调用添加bug接口
		 		User user = (User) request.getSession().getAttribute("user");
	        	result=UseCaseService.edit(user, useCase);
	            // 成功返回
	            return ApiResponse.success(result.getResult());
	        } catch (Exception e) {
	            // 异常
	            return ApiResponse.error();
	        }
		}
	 	
	 	
	 	
	 	 @ApiOperation(value = "分页获取用户列表", notes = "分页获取用户列表")
		    @RequestMapping(value = "useCase/queryAllUserCaseList", method = RequestMethod.GET)
		    @ApiImplicitParams({
		    	 	@ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "int"),
		    	 	@ApiImplicitParam(name = "caseTitle", value = "用例标题", required = false, paramType = "query", dataType = "string"),
		    	 	@ApiImplicitParam(name = "demandId", value = "需求id", required = false, paramType = "query", dataType = "int"),
		            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "long"),
		            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
		            @ApiImplicitParam(name = "caseStatus", value = "用例状态(0:正常；1:被阻塞；2：研究中)", required = false, paramType = "query", dataType = "string"),
		    	 	})
		    public JSONObject queryAllUserCaseList(Long projectId,Long demandId,String caseTitle,@RequestParam(defaultValue = "1") Integer pageSize,@RequestParam(defaultValue = "10") Integer currentPage,String caseStatus) {
				 Map<String,Object> map=new HashMap<String,Object>();
				 if(projectId!=null){
					 map.put("projectId", projectId);
					 
				 }
				 if(demandId!=null){
					 map.put("demandId", demandId);
					 
				 }
				 if(!StringUtils.isEmpty(caseTitle)){
					 map.put("caseTitle", caseTitle);
					 
				 }
				 if(!StringUtils.isEmpty(caseStatus)){
					 map.put("caseStatus", caseStatus);
					 
				 }
		        try {
		            //调用添加bug接口
		        	 List<UseCase> list = UseCaseService.queryAllUserCaseList( pageSize, currentPage,map);
		            PageInfo<UseCase> result = new PageInfo<UseCase>(list);
		            // 成功返回
		            return ApiResponse.success(result);
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
		    }

	
}
