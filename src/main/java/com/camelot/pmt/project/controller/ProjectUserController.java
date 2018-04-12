package com.camelot.pmt.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 项目成员管理
 * @author guodx
 *
 */
@RequestMapping("/api/project/user")
@Api("项目成员管理接口")
@RestController
public class ProjectUserController {

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation("项目添加成员")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "projectId", dataType = "Long", value = "项目id"),
		@ApiImplicitParam(paramType = "query", name = "userId", dataType = "Long", value = "成员id"),
		@ApiImplicitParam(paramType = "query", name = "preJoinTime", dataType = "Date", value = "预计进项目时间"),
		@ApiImplicitParam(paramType = "query", name = "preOutTime", dataType = "Date", value = "预计出项目时间"),
		@ApiImplicitParam(paramType = "query", name = "userProRole", dataType = "Long", value = "角色id（在项目角色）"),
		@ApiImplicitParam(paramType = "query", name = "createUserId", dataType = "Long", value = "创建人id")
	})
	public JSONObject addUser() {
		
		return null;
	}
}
