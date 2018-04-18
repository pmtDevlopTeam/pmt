package com.camelot.pmt.testmanage.bugmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.testmanage.bugmanage.model.BugHistory;
import com.camelot.pmt.testmanage.bugmanage.model.BugManage;
import com.camelot.pmt.testmanage.bugmanage.model.SelectBugManage;
import com.camelot.pmt.testmanage.bugmanage.service.BugHistoryService;
import com.camelot.pmt.testmanage.bugmanage.service.BugManageService;
import com.github.pagehelper.PageInfo;

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
@RequestMapping(value = "/bug")
public class BugManageController {
	 //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BugManageService bugManageService;
	
	@Autowired
	private BugHistoryService bugHistoryService;
	
	
	
	  /**
     * 创建 bug
     * 注意：新增方法以addXxx开头
     *
     * @param Menu menu
     * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
     */
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
	 	@PostMapping(value = "/addBugManage")
	    public JSONObject addBugManage(@ApiIgnore BugManage bugManage) {
			boolean flag = false;
			try {
					flag = bugManageService.addBugManage(bugManage);
		            if (flag) {
		                return ApiResponse.success();
		            }
		            return ApiResponse.error("添加bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
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
	    	@PostMapping(value = "/updateBugManage")
		    public JSONObject updateBugManage(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
		        try {
		            //调用修改bug接口
		            flag = bugManageService.updateBugManage(bugManage);
		            if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("修改bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
		        }
		    }
		
	    
	    	@ApiOperation(value = "撤销bug", notes = "撤销bug")
	    	@ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long")
	            })
	    	@PostMapping(value = "/updateBugStatusRevoke")
		    public JSONObject updateBugStatusRevoke(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
		        try {
		            //调用撤销bug接口
		        	flag = bugManageService.updateBugStatusRevoke(bugManage);
		        	 if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("撤销bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
		        }
		    }
	    	
	    	@ApiOperation(value = "关闭bug", notes = "关闭bug")
	    	@ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	            })
	    	@PostMapping(value = "/updateBugStatusClose")
	    	public JSONObject updateBugStatusClose(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
	    		try {
	    			//调用关闭bug接口
	    			flag = bugManageService.updateBugStatusClose(bugManage);
	    			 if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("修改异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
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
	    	@PostMapping(value = "/updateBugStatusYes")
	    	public JSONObject updateBugStatusYes(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
	    		try {
	    			//调用添加bug接口
	    			flag = bugManageService.updateBugStatusYes(bugManage);
	    			 if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("确认bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
		        }
	    	}
	    	
	    	@ApiOperation(value = "指派bug", notes = "指派bug")
	    	@ApiImplicitParams({
	    		@ApiImplicitParam(name = "id", value = "bugId", required = true, paramType = "form", dataType = "Long"),
	    		@ApiImplicitParam(name = "designatedId", value = "指派人", required = true, paramType = "form", dataType = "String"),
	    		@ApiImplicitParam(name = "bugDescribe", value = "备注", required = false, paramType = "form", dataType = "String")
	    	})
	    	@PostMapping(value = "/updateBugAssign")
	    	public JSONObject updateBugAssign(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
	    		try {
	    			//调用添加bug接口
	    			flag = bugManageService.updateBugAssign(bugManage);
	    			 if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("确认bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
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
	    	@PostMapping(value = "/updateBugSolve")
	    	public JSONObject updateBugSolve(@ApiIgnore  BugManage bugManage) {
	    		boolean flag = false;
	    		try {
	    			//调用添加bug接口
	    			flag = bugManageService.updateBugSolve(bugManage);
	    			 if(flag){
		            	 return ApiResponse.success();
		            }
		            return ApiResponse.error("确认bug异常");
		        } catch (Exception e) {
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
		        }
	    	}
	    	
	    @ApiOperation(value = "分页获取bug列表", notes = "分页获取bug列表")
	    @RequestMapping(value = "/queryUsersByPage", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "currentPage", value = "页码", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, paramType = "query", dataType = "int"),
	            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "status", value = "条件（1.由我负责 2.由我创建 3.由我解决 4.未指派 5.未解决）", required = false, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "bugNo", value = "bug编号", required = false, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "bugStatus", value = "bug状态", required = false, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "bugTitle", value = "bug名称", required = false, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "qStartTime", value = "开始日期", required = false, paramType = "query", dataType = "String"),
	            @ApiImplicitParam(name = "qEndTime", value = "结束日期", required = false, paramType = "query", dataType = "String")
	    })
	    public JSONObject queryUsersByPage(@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "1") Integer currentPage,Integer status,String qStartTime,String qEndTime,String bugStatus,String bugTitle,String bugNo,String projectId) {
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("currentPage", currentPage);
			 map.put("pageSize", pageSize);
			 map.put("projectId", projectId);
			 //用户id
			 //map.put("userId", 1);
			 if(status!=null){
				 map.put("status",status);
			 }
			 if(!StringUtils.isEmpty(qStartTime)){
				 map.put("qStartTime",qStartTime);
			 }
			 if(!StringUtils.isEmpty(qEndTime)){
				 map.put("qEndTime",qEndTime);
			 }
			 if(!StringUtils.isEmpty(bugStatus)){
				 map.put("bugStatus",bugStatus);
			 }
			 if(!StringUtils.isEmpty(bugTitle)){
				 map.put("bugTitle",bugTitle);
			 }
			 if(!StringUtils.isEmpty(bugNo)){
				 map.put("bugNo", bugNo);
			 }
	        try {
	            //调用查询bug分页接口
	             List<SelectBugManage> selectCondition = bugManageService.queryBugManageCondition(map);
	             PageInfo<SelectBugManage> result = new PageInfo<SelectBugManage>(selectCondition);
	            // 成功返回
	             return ApiResponse.success(result);
	        } catch (Exception e) {
	        	logger.error(e.getMessage());
	            return ApiResponse.jsonData(APIStatus.ERROR_500);
	        }
	    }

	  /**
	     * <p>
	     * Description:[查询bug历史记录]
	     * </p>
	     * 
	     * @param username
	     *            用户名,password 密码,role 角色,phone 电话,email 邮箱
	     * @return {"status": {"message": "请求处理成功.","code": 200}, "data": {userModel列表}]
	     */
	    @ApiOperation(value = "根据bugId查询bug历史记录", notes = "根据bugId查询bug历史记录")
	    @RequestMapping(value = "/queryBugHistoryAll", method = RequestMethod.GET)
	    public JSONObject queryBugHistoryAll( @ApiParam(name = "bugId", value = "bugId", required = true) @RequestParam(required = true)  Long bugId) {
	        try {
	        	List<BugHistory> result =bugHistoryService.queryBugHistoryAll(bugId);
	        	 return ApiResponse.success(result);
	        } catch (Exception e) {
	            return ApiResponse.error();
	        }
	    }
	
	
	
	
	
		/**
		 * 根据id查询bug信息
		 * @param userId
		 * @return
		 */
	    @ApiOperation(value = "根据id查询bug信息", notes = "根据id查询bug信息")
	    @RequestMapping(value = "getBugById", method = RequestMethod.GET)
	    public JSONObject getBugById(
	            @ApiParam(name = "id", value = "bugId", required = true) @RequestParam(required = true)  Long id) {
	        try {
	        	BugManage result = bugManageService.queryBugById(id);
	            return ApiResponse.success(result);
	        } catch (Exception e) {
	        	logger.error(e.getMessage());
	            return ApiResponse.jsonData(APIStatus.ERROR_500);
	        }
	    }
	    
	   
	    

}