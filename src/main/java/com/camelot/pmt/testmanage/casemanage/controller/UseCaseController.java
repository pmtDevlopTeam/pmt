package com.camelot.pmt.testmanage.casemanage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.camelot.pmt.caserepertory.PageBean;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.testmanage.casemanage.model.UseCase;
import com.camelot.pmt.testmanage.casemanage.service.UseCaseService;
import com.camelot.pmt.testmanage.casemanage.util.ActionBean;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
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
	private ShiroUtils shiroUtils;
	
	 //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UseCaseService UseCaseService;
	
	@ApiOperation(value = "新增用例")
	@PostMapping(value = "userCase/add")
	public JSONObject addUseCase(HttpServletRequest request,  @ApiParam(value = "useCase", required = true) UseCase useCase) {
	 	 try {
	 		 	User user = (User) request.getSession().getAttribute("user");
	           boolean flag = UseCaseService.addUseCase(user, useCase);
	            if(flag){
	                return ApiResponse.success();
	            }
	            return ApiResponse.error("新增异常");
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	            return ApiResponse.jsonData(APIStatus.ERROR_500);
	        }
	}

	@ApiOperation(value = "批量新增用例")
	@PostMapping(value = "userCase/addBatch")
	public JSONObject addBatchUseCase(HttpServletRequest request, @RequestBody @ApiParam(value = "list", required = true) List<UseCase> list) {
		try {
			User user = (User) shiroUtils.getSessionAttribute("user");
           boolean flag = UseCaseService.addBatchUseCase(user, list);
            if(flag){
                return ApiResponse.success();
            }
            return ApiResponse.error("批量新增异常");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ApiResponse.jsonData(APIStatus.ERROR_500);
        }
	}
	 
	 
	 @ApiOperation(value = "根据id删除用例信息", notes = "根据id删除用例信息")
	    @RequestMapping(value = "userCase/updateUserCaseDelFlag", method = RequestMethod.GET)
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
	    public JSONObject updateUserCaseDelFlag(Long id) {
		 	try {
	            //调用修改用例接口
		 		boolean	result=UseCaseService.updateUserCaseDelFlag(id);
	            // 成功返回
	            return ApiResponse.success(result);
	        } catch (Exception e) {
	            // 异常
	            return ApiResponse.error();
	        }
	    }
	 
	 
	 	@ApiOperation(value = "编辑用例")
		@PostMapping(value = "userCase/updateUserCase")
		public JSONObject updateUserCase(HttpServletRequest request,@ApiIgnore @ApiParam(value = "useCase", required = true) UseCase useCase) {
		 	try {
		 		User user = (User) shiroUtils.getSessionAttribute("user");
	            //调用修改接口
		 		boolean	result=UseCaseService.updateUserCase(user, useCase);
	            // 成功返回
	            return ApiResponse.success(result);
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
	 	 
	 	 
	 	 
		 @ApiOperation(value = "通过id获取用例信息", notes = "通过id获取用例信息")
		    @RequestMapping(value = "userCase/queryByUseCaseId", method = RequestMethod.GET)
		    @ApiImplicitParams({
		            @ApiImplicitParam(name = "id", value = "用例id", required = true, paramType = "query", dataType = "long") })
		    public JSONObject queryByUseCaseId(Long id) {
			 	 ExecuteResult<UseCase> result = new ExecuteResult<UseCase>();
			        try {
			            //调用添加bug接口
			            // 成功返回
			            return ApiResponse.success(UseCaseService.queryUseCaseByUseCaseId(id));
			        } catch (Exception e) {
			            // 异常
			            return ApiResponse.error();
			        }
		    }
		 

	
}
