package com.camelot.pmt.warning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.warning.model.BugWarning;
import com.camelot.pmt.warning.service.BugWarningService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 预警设置
 * 
 * @author maple
 */
@RestController
@Api(value = "预警设置接口", description = "预警设置接口")
@RequestMapping(value = "/bugWarning")
public class BugWarningController {

	 //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    BugWarningService bugWarningService;
	  /**
   * 创建 bug
   * 注意：新增方法以addXxx开头
   *
   * @param Menu menu
   * @return JSONObject {"status":{"code":xxx,"message":"xxx"},"data":{xxx}}
   */
	@ApiOperation(value = "添加bugWarning", notes = "添加bugWarning")
	@ApiImplicitParams({
	            @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "form", dataType = "Long"),
	            @ApiImplicitParam(name = "num", value = "超期数", required = true, paramType = "form", dataType = "int")
	          })
	 	@PostMapping(value = "/addbugWarning")
	    public JSONObject addbugWarning(@ApiIgnore BugWarning bugWarning) {
			boolean flag = false;
			try {
					Integer count=bugWarningService.queryBugCount(Long.parseLong("666"));
					flag = bugWarningService.addBugWarning(bugWarning);
		            if (flag) {
		                return ApiResponse.success();
		            }
		            return ApiResponse.error("添加bugWarning异常");
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	 logger.error(e.getMessage());
		             return ApiResponse.jsonData(APIStatus.ERROR_500);
		        }
	    }
}
