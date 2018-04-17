package com.camelot.pmt.platform.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.APIStatus;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.OrgService;
import com.camelot.pmt.util.Tree;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/platform/org")
@Api(value = "组织机构管理接口", description = "组织机构管理接口")
public class OrgController {
	@Autowired
	private OrgService orgService;

	/**
	 * 查询单个部门
	 * @param String orgId 用户UUID
	 * @return Org 对象
	 */
	
	@ApiOperation(value = "根据orgId查询单个部门机构", notes = "查询单个部门机构")
	@RequestMapping(value = "/queryOrgByOrgId", method = RequestMethod.POST)
	public JSONObject queryOrgByOrgId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		ExecuteResult<Org> result = new ExecuteResult<Org>();
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.queryOrgByOrgId(orgId);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}
	
	/**
	 * 查询多个子部门  递归查询部门
	 * @param orgId 用户UUID
	 * @return List<Org>
	 */
	@ApiOperation(value = "根据parentId查询子部门机构", notes = "查询子部门机构")
	@RequestMapping(value = "/queryOrgAndChildrenByOrgId", method = RequestMethod.POST)
	public JSONObject queryOrgAndChildrenByOrgtId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.queryOrgAndChildrenById(orgId);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
	 * 查询所有部门机构
	 * @return List<Org>
	 */
	@ApiOperation(value = "查询所有部门机构", notes = "查询所有部门机构")
	@RequestMapping(value = "/queryOrgAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject queryOrgAll() {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
		try {
			result = orgService.queryAllOrgs();
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
	 * 添加部门机构
	 * @param Org
	 */
	@ApiOperation(value = "添加部门机构", notes = "添加部门机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgname", value = "部门名称", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "parentId", value = "上级部门", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "state", value = "部门状态", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "sortNum", value = "部门排序号", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "orgCode", value = "部门编号", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "creatUserId", value = "创建人", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "modifyUserId", value = "修改人", required = true, paramType = "form", dataType = "String")
			})
	@RequestMapping(value = "/creatOrg", method = RequestMethod.POST)
	public JSONObject creatOrg(@ApiIgnore Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			
			if (org == null) {
				return ApiResponse.errorPara();
			}
			result = orgService.creatOrg(org);
			return ApiResponse.success(result.getResult());
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
	 * 删除部门机构
	 */
	@ApiOperation(value = "删除部门机构", notes = "删除单个部门机构(只是当前节点)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "query", dataType = "String") })
	@RequestMapping(value = "/deleteOrgByorgId", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrgByorgId(@ApiIgnore Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (StringUtils.isEmpty(org.getOrgId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.deleteOrgByOrgId(org);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();	
		}
	}
	
	/**
	 * 删除多个子部门机构  递归删除
	 */
	@ApiOperation(value = "删除多个子部门机构", notes = "删除多个子部门机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "query", dataType = "String") })
	@RequestMapping(value = "/deleteOrgByOrgId", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrgByOrgId(@ApiIgnore String orgId) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.deleteOrgByOrgId(orgId);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
	 * 编辑部门机构
	 * @param Org
	 * @return JSONObject
	 * 
	 */
	@ApiOperation(value = "编辑部门机构", notes = "编辑部门机构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orgname", value = "部门名称", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "上级部门", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "state", value = "部门状态", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "sortNum", value = "部门排序号", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "orgCode", value = "部门编号", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "creatUserId", value = "创建人", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "modifyUserId", value = "修改人", required = true, paramType = "form", dataType = "String")
		})
	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject modifyOrgByOrgId(@ApiIgnore Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (StringUtils.isEmpty(org.getOrgname()) && StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getOrgCode())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.modifyOrgByOrgId(org);
			if (result.isSuccess()) {
				return ApiResponse.success(result.getResult());
			}
			return ApiResponse.error();
		} catch (Exception e) {
			return ApiResponse.error();
		}
	}

	/**
     * <p>Description:[分页查询部门列表]</p>
     * @param  page 页码,rows 每页数量
     * @return "data": {"total": 总数量,"rows":[查询的结果集],"status": {"code": 200,"message": "请求处理成功."}}
     */
    @ApiOperation(value="分页获取部门列表", notes="分页获取部门列表")
    @RequestMapping(value = "/queryOrgsByPage",method = RequestMethod.POST)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", dataType = "int"),
    	@ApiImplicitParam(name = "rows", value = "每页数量", required = true, paramType = "query", dataType = "int")
    })
    public JSONObject queryOrgsByPage(@ApiIgnore Pager page){
    	ExecuteResult<DataGrid<Org>> result = new ExecuteResult<DataGrid<Org>>();
    	try {
    		if(page == null) {
    			return ApiResponse.errorPara();
    		}
    		result = orgService.queryOrgsByPage(page);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}
    }
    
	 /** 组织机构列表详情(关系到用户  即部门负责人)
	 * @param OrgToUser
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value="获取组织机构列表详情", notes="获取组织机构列表详情")
    @RequestMapping(value = "/queryOrgsDetail",method = RequestMethod.POST)
    
    public JSONObject queryOrgsDetail(){
    	ExecuteResult<List<OrgToUser>> result = new ExecuteResult<List<OrgToUser>>();
    	try {
    		result = orgService.queryOrgsDetail();
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    /** 添加组织机构与用户的绑定(关系到用户 )
	 * @param orgId   userIds
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value = "添加组织机构绑定用户", notes = "组织机构绑定用户")
    @PostMapping(value = "/addOrgToUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户ids（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string")})
    
    public JSONObject addOrgToUser(@ApiIgnore Org org){
    	ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getUserId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.addOrgToUser(org);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    /** 修改组织机构与用户的绑定(关系到用户 )
	 * @param orgId   userIds
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value = "修改组织机构绑定用户", notes = "组织机构绑定用户")
    @PostMapping(value = "/modifyOrgToUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户ids（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string")})
    
    public JSONObject modifyOrgToUser(@ApiIgnore Org org){
    	ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getUserId())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.modifyOrgToUser(org);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    
    
    /** 修改组织机构的状态
	 * @param orgId state
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value = "修改组织机构的状态", notes = "修改组织机构的状态")
    @PostMapping(value = "/modifyOrgByOrgIdAndState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "部门状态  0（默认）启用 1 停用 2 锁定", required = true, paramType = "form", dataType = "string")})
    
    public JSONObject modifyOrgByOrgIdAndState(@ApiIgnore Org org){
    	ExecuteResult<String> result = new ExecuteResult<String>();
    	try {
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getState())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.modifyOrgByOrgIdAndState(org);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    
    /** 组织机构与用户的绑定 根据orgId查询所有用户(关系到用户 )
	 * @param orgId 
	 * @return List<User>
	 * 
	 **/
    @ApiOperation(value = "组织机构绑定用户  根据orgId查询所有用户", notes = "组织机构绑定用户  根据orgId查询所有用户")
    @PostMapping(value = "/queryOrgToUserByOrgId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "string")
            })
    
    public JSONObject queryOrgToUserByOrgId(String orgId){
    	ExecuteResult<List<User>> result = new ExecuteResult<List<User>>();
    	try {
    		if (StringUtils.isEmpty(orgId)) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.queryOrgToUserByOrgId(orgId);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    
    /** 组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息
	 * @param orgId,orgCode,orgname
	 * @return List<Org>
	 * 
	 **/
    @ApiOperation(value = "组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息", notes = "组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息组织机构绑定用户  根据orgId查询所有用户")
    @PostMapping(value = "/queryOrgByParameters")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "orgCode", value = "部门编号", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "orgname", value = "部门名称", required = false, paramType = "form", dataType = "string")
            })
    
    public JSONObject queryOrgToUser(@ApiIgnore Org org){
    	ExecuteResult<List<Org>> result = new ExecuteResult<List<Org>>();
    	try {
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getOrgCode()) && StringUtils.isEmpty(org.getOrgname())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.queryOrgByParameters(org);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.error();
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}

    }
    
    /** 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 * @param OrgToUser
	 * @return JSONObject
	 **/
    @ApiOperation(value="组织机构   根据orgId查看详情", notes="组织机构   根据orgId查看详情")
    @RequestMapping(value = "/queryOrgsDetailByOrgId",method = RequestMethod.POST)
    public JSONObject queryOrgsDetail(String orgId){
    	ExecuteResult<List<OrgToUser>> result = new ExecuteResult<List<OrgToUser>>();
    	try {
    		if (StringUtils.isEmpty(orgId)) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    		result = orgService.queryOrgsDetailByOrgId(orgId);
    		if(result.isSuccess()) {
    			return ApiResponse.success(result.getResult());
    		}
    		return ApiResponse.success(result.getResult());
    	}catch (Exception e) {
    		return ApiResponse.error();
    	}
    }
}
