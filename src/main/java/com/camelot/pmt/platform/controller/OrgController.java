package com.camelot.pmt.platform.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.APIStatus;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgToUser;
import com.camelot.pmt.platform.service.OrgService;
import com.camelot.pmt.platform.util.Tree;

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
	 * @param orgId 用户UUID
	 */
	
	@ApiOperation(value = "根据orgId查询单个部门机构", notes = "查询单个部门机构")
	@RequestMapping(value = "/queryOrgById", method = RequestMethod.POST)
	public JSONObject queryUserByOrgId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		ExecuteResult<Org> result = new ExecuteResult<Org>();
		try {
			result = orgService.findOrgById(orgId);
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
	 */
	@ApiOperation(value = "根据parentId查询子部门机构", notes = "查询子部门机构")
	@RequestMapping(value = "/queryOrgAndChildrenByOrgId", method = RequestMethod.POST)
	public JSONObject queryUserByParentId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
		try {
			result = orgService.selectOrgAndChildrenById(orgId);
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
	 */
	@ApiOperation(value = "查询所有部门机构", notes = "查询所有部门机构")
	@RequestMapping(value = "/queryOrgAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject queryOrgAll() {
		ExecuteResult<List<Tree<Org>>> result = new ExecuteResult<List<Tree<Org>>>();
		try {
			result = orgService.findAllOrgs();
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
	@RequestMapping(value = "/addOrg", method = RequestMethod.POST)
	public JSONObject addOrg(@ApiIgnore Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (org == null) {
				return ApiResponse.errorPara();
			}
			result = orgService.addOrg(org);
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
	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrg(@ApiIgnore Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if ("".equals(org.getOrgId()) && ("0").equals(org.getOrgId())) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
			}
			result = orgService.deleteOrg(org);
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
			if ("".equals(orgId) && orgId != null) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
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
	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateOrg(Org org) {
		ExecuteResult<String> result = new ExecuteResult<String>();
		try {
			if (org == null) {
				return ApiResponse.jsonData(APIStatus.ERROR_400);
			}
			result = orgService.editOrg(org);
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
    
    /** 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 * @param OrgToUser
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value="组织机构   根据orgId查看详情", notes="组织机构   根据orgId查看详情")
    @RequestMapping(value = "/queryOrgsDetailByOrgId",method = RequestMethod.POST)
    public JSONObject queryOrgsDetail(String orgId){
    	ExecuteResult<List<OrgToUser>> result = new ExecuteResult<List<OrgToUser>>();
    	try {
    		if("".equals(orgId) && orgId ==null){
    			return ApiResponse.error("传入的参数不正确");
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
