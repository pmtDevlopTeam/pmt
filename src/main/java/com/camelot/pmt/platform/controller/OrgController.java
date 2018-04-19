package com.camelot.pmt.platform.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.camelot.pmt.platform.shiro.ShiroUtils;
import com.camelot.pmt.util.Tree;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author pmt
 * @Description: 基础平台-组织管理管理接口
 * @date 2018-04-11
 */
@RestController
@RequestMapping(value = "/platform/org")
@Api(value = "组织机构管理接口", description = "组织机构管理接口")
public class OrgController {
	//日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrgService orgService;
	/**
	 * 查询单个部门
	 * @param String orgId 
	 * @return Org 对象
	 */
	@ApiOperation(value = "根据orgId查询单个部门机构", notes = "查询单个部门机构")
	@RequestMapping(value = "/queryOrgByOrgId", method = RequestMethod.POST)
	public JSONObject queryOrgByOrgId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
				return ApiResponse.success(orgService.queryOrgByOrgId(orgId));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
		}
	}
	/**
	 * 查询多个子部门  递归查询部门
	 * @param orgId 
	 * @return List<Org>
	 */
	@ApiOperation(value = "根据parentId查询子部门机构接口", notes = "查询本部门以及孩子部门机构")
	@RequestMapping(value = "/queryOrgAndChildrenByOrgId", method = RequestMethod.POST)
	public JSONObject queryOrgAndChildrenByOrgtId(
			@ApiParam(value = "orgId", required = true) @RequestParam(required = true) String orgId) {
		List<Tree<Org>> result = new ArrayList<Tree<Org>>();
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.queryOrgAndChildrenById(orgId);
				return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
		List<Tree<Org>> result = new ArrayList<Tree<Org>>();
		try {
			result = orgService.queryAllOrgs();
				return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
		}
	}

	/**
	 * 添加部门机构
	 * @param Org
	 */
	@ApiOperation(value = "添加部门组织机构接口", notes = "添加部门组织机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgname", value = "部门名称", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "parentId", value = "上级部门", required = true,defaultValue = "0", paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "state", value = "部门状态", required = true, defaultValue = "0",paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "sortNum", value = "部门排序号", required = true, paramType = "form", dataType = "String"),
			@ApiImplicitParam(name = "orgCode", value = "部门编号", required = true, paramType = "form", dataType = "String")
			
			})
	@RequestMapping(value = "/addOrg", method = RequestMethod.POST)
	public JSONObject addOrg(@ApiIgnore Org org) {
		String result="";
		try {
			User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            org.setCreatUserId(user.getUserId());
            org.setModifyUserId(user.getUserId());
            if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getOrgCode()) && StringUtils.isEmpty(org.getOrgname()) && StringUtils.isEmpty(org.getSortNum()) && StringUtils.isEmpty(org.getState())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.addOrg(org);
			return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
		try {
			String result="";
			if (StringUtils.isEmpty(org.getOrgId())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.deleteOrgByOrgId(org);
			return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());	
		}
	}
	/**
	 * 删除多个子部门机构  递归删除
	 */
	@ApiOperation(value = "删除部门本身以及孩子部门", notes = "删除部门本身以及孩子部门")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "query", dataType = "String") })
	@RequestMapping(value = "/deleteOrgByOrgId", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrgByOrgId(@ApiIgnore String orgId) {
		String result="";
		try {
			if (StringUtils.isEmpty(orgId)) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.deleteOrgByOrgId(orgId);
				return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
		@ApiImplicitParam(name = "orgname", value = "部门名称", required = false, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "parentId", value = "上级部门", required = false, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "state", value = "部门状态", required = false, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "sortNum", value = "部门排序号", required = false, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "orgCode", value = "部门编号", required = false, paramType = "form", dataType = "String"),
		@ApiImplicitParam(name = "modifyUserId", value = "修改人", required = false, paramType = "form", dataType = "String")
		})
	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateOrgByOrgId(@ApiIgnore Org org) {
		String result = "";
		try {
			User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            org.setCreatUserId(user.getUserId());
            org.setModifyUserId(user.getUserId());
			if (StringUtils.isEmpty(org.getOrgname()) && StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getOrgCode())) {
                ApiResponse.jsonData(APIStatus.ERROR_400);
            }
			result = orgService.updateOrgByOrgId(org);
				return ApiResponse.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
		}
	}

	/**
     * <p>Description:[分页查询部门列表]</p>
     * @param  page 页码,rows 每页数量
     * @return JSONObject
     */
    @ApiOperation(value="分页获取部门列表", notes="分页获取部门列表")
    @RequestMapping(value = "/queryOrgsByPage",method = RequestMethod.POST)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1" ,required = true, paramType = "query", dataType = "int"),
    	@ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10" ,required = true, paramType = "query", dataType = "int")
    })
    public JSONObject queryOrgsByPage(int pageNum,int pageSize){
    	PageInfo result = new PageInfo();
    	try {
    		result = orgService.queryOrgsByPage(pageNum,pageSize);
    			return ApiResponse.success(result);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
    	}
    }
    
	 /** 组织机构列表详情(关系到用户 )
	 * @param OrgToUser
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value="获取组织机构列表详情(关系到用户 )", notes="获取组织机构列表详情(关系到用户 )")
    @RequestMapping(value = "/queryOrgsDetail",method = RequestMethod.POST)
    
    public JSONObject queryOrgsDetail(){
    	List<OrgToUser> result = new ArrayList<OrgToUser>();
    	try {
    		result = orgService.queryOrgsDetail();
    			return ApiResponse.success(result);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
    	try {
    		User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            org.setCreatUserId(user.getUserId());
            org.setModifyUserId(user.getUserId());
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getUserId()) && StringUtils.isEmpty(org.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    			return ApiResponse.success(orgService.addOrgToUser(org));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
    	}

    }
    
    /** 修改组织机构与用户的绑定(关系到用户 )
	 * @param orgId   userIds
	 * @return JSONObject
	 * 
	 **/
    @ApiOperation(value = "修改组织机构绑定用户", notes = "组织机构绑定用户")
    @PostMapping(value = "/updateOrgToUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "部门id", required = true, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "userIds", value = "用户ids（格式：1,2,3,4）", required = true, paramType = "form", dataType = "string")})
    
    public JSONObject updateOrgToUser(@ApiIgnore Org org){
    	try {
    		User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            org.setCreatUserId(user.getUserId());
            org.setModifyUserId(user.getUserId());
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getUserId()) && StringUtils.isEmpty(org.getUserIds())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    			return ApiResponse.success(orgService.updateOrgToUser(org));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
            @ApiImplicitParam(name = "state", value = "部门状态  0（默认）启用 1 停用 2 锁定", required = false, paramType = "form", dataType = "string")})
    
    public JSONObject updateOrgByOrgIdAndState(@ApiIgnore Org org){
    	try {
    		User user = (User) ShiroUtils.getSessionAttribute("user");
            if (StringUtils.isEmpty(user.getUserId())) {
                ApiResponse.jsonData(APIStatus.UNAUTHORIZED_401);
            }
            org.setCreatUserId(user.getUserId());
            org.setModifyUserId(user.getUserId());
    		if (StringUtils.isEmpty(org.getOrgId()) && StringUtils.isEmpty(org.getState())) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    			return ApiResponse.success(orgService.updateOrgByOrgIdAndState(org));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
    	try {
    		if (StringUtils.isEmpty(orgId)) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    			return ApiResponse.success(orgService.queryOrgToUserByOrgId(orgId));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
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
            @ApiImplicitParam(name = "orgname", value = "部门名称", required = false, paramType = "form", dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "页码", defaultValue = "1" ,required = true, paramType = "query", dataType = "int"),
        	@ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10" ,required = true, paramType = "query", dataType = "int")
            })
    
    public JSONObject queryOrgToUser(@ApiIgnore Org org ,int pageNum,int pageSize){
    	PageInfo result = new PageInfo();
    	try {
    			return ApiResponse.success(orgService.queryOrgByParameters(org,pageNum,pageSize));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
    	}
    }
    /** 组织机构   根据orgId查看详情(关系到用户 )
	 * @param OrgToUser
	 * @return JSONObject
	 **/
    @ApiOperation(value="组织机构   根据orgId查看详情", notes="组织机构   根据orgId查看详情")
    @RequestMapping(value = "/queryOrgsDetailByOrgId",method = RequestMethod.POST)
    public JSONObject queryOrgsDetail(String orgId){
    	try {
    		if (StringUtils.isEmpty(orgId)) {
                return ApiResponse.jsonData(APIStatus.ERROR_400);
            }
    			return ApiResponse.success(orgService.queryOrgsDetailByOrgId(orgId));
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		return ApiResponse.jsonData(APIStatus.ERROR_500, e.getMessage());
    	}
    }
}
