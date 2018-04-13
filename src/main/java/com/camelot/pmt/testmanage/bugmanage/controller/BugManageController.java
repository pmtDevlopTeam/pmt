package com.camelot.pmt.testmanage.bugmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


/**
 * bug管理模块
 * 
 * @author maple
 */
@RestController
@Api(value = "bug管理接口", description = "bug管理接口")
public class BugManageController {
	
	@Autowired
	private BugManageService bugManageService;
	
	@ApiOperation(value = "添加bug", notes = "添加bug")
	@ApiImplicitParams({
	            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "demandId", value = "需求id", required = false, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "taskId", value = "任务id", required = false, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "task1Id", value = "所属一级任务id", required = false, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "designatedId", value = "指派给", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "versionId", value = "影响版本", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "bugTitle", value = "bug标题", required = true, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "bugType", value = "bug类型", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "bugDescribe", value = "bug描述", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "caseTerminal", value = "测试终端", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "caseEnvironment", value = "测试环境", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "endTime", value = "截至日期", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "bugLevel", value = "优先级", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "seriousDegree", value = "严重程度", required = false, paramType = "form", dataType = "String"),
	            @ApiImplicitParam(name = "stepsReproduce", value = "重现步骤", required = false, paramType = "form", dataType = "String") })
	    @RequestMapping(value = "bug/addBug", method = RequestMethod.POST)
	    public JSONObject addUser(@ApiIgnore BugManage bugManage) {
	        ExecuteResult<String> result = new ExecuteResult<String>();
	        try {
	            //调用添加bug接口
	            result = bugManageService.save(bugManage);
	            // 成功返回
	            return ApiResponse.success(result.getResult());
	        } catch (Exception e) {
	            // 异常
	            return ApiResponse.error();
	        }
	    }
	
	
		/**
		 * 根据id查询bug信息
		 * @param userId
		 * @return
		 */
	    @ApiOperation(value = "根据id查询bug信息", notes = "根据id查询bug信息")
	    @RequestMapping(value = "bug/getBugById", method = RequestMethod.GET)
	    public JSONObject getBugById(
	            @ApiParam(name = "id", value = "bugId", required = true) @RequestParam(required = true)  Long id) {
	        ExecuteResult<BugManage> result = new ExecuteResult<BugManage>();
	        try {
	            result = bugManageService.getBugById(id);
	            if (result.isSuccess()) {
	                return ApiResponse.success(result.getResult());
	            }
	            return ApiResponse.error();
	        } catch (Exception e) {
	            return ApiResponse.error();
	        }
	    }
	    
	   /**
	    * 修改bug
	    */
	    @ApiOperation(value = "修改bug", notes = "修改bug")
		@ApiImplicitParams({
					@ApiImplicitParam(name = "id", value = "bugid", required = true, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "demandId", value = "需求id", required = false, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "taskId", value = "任务id", required = false, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "task1Id", value = "所属一级任务id", required = false, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "caseId", value = "用例id", required = false, paramType = "form", dataType = "Long"),
		            
		            @ApiImplicitParam(name = "bugTitle", value = "bug标题", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "stepsReproduce", value = "重现步骤", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "bugDescribe", value = "bug描述", required = false, paramType = "form", dataType = "String"),
		           
		            @ApiImplicitParam(name = "versionId", value = "影响版本", required = true, paramType = "form", dataType = "Long"),
		            @ApiImplicitParam(name = "bugType", value = "bug类型", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "seriousDegree", value = "严重程度", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "bugLevel", value = "优先级", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "bugStatus", value = "bug状态", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "designatedId", value = "指派给", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "endTime", value = "截至日期", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "caseTerminal", value = "测试终端", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "caseEnvironment", value = "测试环境", required = false, paramType = "form", dataType = "String"),
		            
		            @ApiImplicitParam(name = "solveId", value = "解决者", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "solveTime", value = "解决日期", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "solveProgram", value = "解决方案", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "closeId", value = "关闭人", required = false, paramType = "form", dataType = "String"),
		            @ApiImplicitParam(name = "closeTime", value = "关闭日期", required = false, paramType = "form", dataType = "String") })
		    @RequestMapping(value = "bug/editBug", method = RequestMethod.POST)
		    public JSONObject editUser(@ApiIgnore  BugManage bugManage) {
		        ExecuteResult<String> result = new ExecuteResult<String>();
		        try {
		            //调用修改bug接口
		            result = bugManageService.edit(bugManage);
		            // 成功返回
		            return ApiResponse.success(result.getResult());
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
		    }
		
	    
	    	@ApiOperation(value = "撤销bug", notes = "撤销bug")
		    @RequestMapping(value = "bug/updateBugStatusRevoke", method = RequestMethod.POST)
		    public JSONObject updateBugStatusRevoke(@ApiParam(name = "id", value = "bugId", required = true) @RequestParam(required = true)  Long id) {
		        ExecuteResult<String> result = new ExecuteResult<String>();
		        try {
		            //调用添加bug接口
		            result = bugManageService.updateBugStatusRevoke(id);
		            // 成功返回
		            return ApiResponse.success(result.getResult());
		        } catch (Exception e) {
		            // 异常
		            return ApiResponse.error();
		        }
		    }
	    	
	    	@ApiOperation(value = "关闭bug", notes = "关闭bug")
	    	@ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	            })
	    	@RequestMapping(value = "bug/updateBugStatusClose", method = RequestMethod.POST)
	    	public JSONObject updateBugStatusClose(@ApiIgnore  BugManage bugManage) {
	    		ExecuteResult<String> result = new ExecuteResult<String>();
	    		try {
	    			//调用添加bug接口
	    			result = bugManageService.updateBugStatusClose(bugManage);
	    			// 成功返回
	    			return ApiResponse.success(result.getResult());
	    		} catch (Exception e) {
	    			// 异常
	    			return ApiResponse.error();
	    		}
	    	}
	    	
	    	
	    	@ApiOperation(value = "确认bug", notes = "确认bug")
	    	@ApiImplicitParams({
	    		@ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	    		@ApiImplicitParam(name = "designatedId", value = "指派人", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugType", value = "bug类型", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugLevel", value = "优先级", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	    	})
	    	@RequestMapping(value = "bug/updateBugStatusYes", method = RequestMethod.POST)
	    	public JSONObject updateBugStatusYes(@ApiIgnore  BugManage bugManage) {
	    		ExecuteResult<String> result = new ExecuteResult<String>();
	    		try {
	    			//调用添加bug接口
	    			result = bugManageService.updateBugStatusYes(bugManage);
	    			// 成功返回
	    			return ApiResponse.success(result.getResult());
	    		} catch (Exception e) {
	    			// 异常
	    			return ApiResponse.error();
	    		}
	    	}
	    	
	    	@ApiOperation(value = "指派bug", notes = "指派bug")
	    	@ApiImplicitParams({
	    		@ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	    		@ApiImplicitParam(name = "designatedId", value = "指派人", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugType", value = "bug类型", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugLevel", value = "优先级", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	    	})
	    	@RequestMapping(value = "bug/updateBugAssign", method = RequestMethod.POST)
	    	public JSONObject updateBugAssign(@ApiIgnore  BugManage bugManage) {
	    		ExecuteResult<String> result = new ExecuteResult<String>();
	    		try {
	    			//调用添加bug接口
	    			result = bugManageService.updateBugAssign(bugManage);
	    			// 成功返回
	    			return ApiResponse.success(result.getResult());
	    		} catch (Exception e) {
	    			// 异常
	    			return ApiResponse.error();
	    		}
	    	}
	    	
	    	@ApiOperation(value = "解决bug", notes = "解决bug")
	    	@ApiImplicitParams({
	    		@ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	    		@ApiImplicitParam(name = "designatedId", value = "指派人", required = false, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "solveProgram", value = "解决方案", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "solveTime", value = "解决日期", required = false, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	    	})
	    	@RequestMapping(value = "bug/updateBugSolve", method = RequestMethod.POST)
	    	public JSONObject updateBugSolve(@ApiIgnore  BugManage bugManage) {
	    		ExecuteResult<String> result = new ExecuteResult<String>();
	    		try {
	    			//调用添加bug接口
	    			result = bugManageService.updateBugSolve(bugManage);
	    			// 成功返回
	    			return ApiResponse.success(result.getResult());
	    		} catch (Exception e) {
	    			// 异常
	    			return ApiResponse.error();
	    		}
	    	}
	    

}