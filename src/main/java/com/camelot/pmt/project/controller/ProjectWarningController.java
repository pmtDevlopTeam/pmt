/**
 * @Package: com.camelot.pmt.project.controller 
 * @author: jh 
 * @date: 2018年4月12日 下午4:40:30 
 */
package com.camelot.pmt.project.controller;

import java.util.Map;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.user.model.UserModel;
import com.camelot.pmt.platform.utils.DataGrid;
import com.camelot.pmt.platform.utils.ExecuteResult;
import com.camelot.pmt.project.service.ProjectWarningService;

/** 
 * @ClassName: ProjectWarningController 
 * @Description: 项目预警controller
 * @author lixiaokang
 * @date 2018年4月12日 下午4:40:30  
 */
@RestController
@RequestMapping("/projectwarning")
public class ProjectWarningController {
	
	@Autowired
	private ProjectWarningService projectWarningService;

	/*
	 * 开启项目预警
	 */
	@ApiOperation(value = "开启项目预警功能", notes = "开启项目预警功能")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "proId", value = "项目id", required = true, paramType = "form", dataType = "int"),
        @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "form", dataType = "Long"),
        @ApiImplicitParam(name = "warnType", value = "预警类型", required = true, paramType = "form", dataType = "String"),
        @ApiImplicitParam(name = "warnStatus", value = "预警状态", required = true, paramType = "form", dataType = "String"),
        @ApiImplicitParam(name = "modifyUserId", value = "修改人id", required = true, paramType = "form", dataType = "String")})
	@PostMapping("start")
	public JSONObject start(@RequestBody String param){
		 ExecuteResult<Map<String, Object>> result = new ExecuteResult<Map<String, Object>>();
		if("".equals(param)){
			return ApiResponse.error();
		}
		projectWarningService.startProjectWarning(param);
        if (result.isSuccess()) {
            return ApiResponse.success(result.getResult());
        }
        return ApiResponse.error();
	}
}
