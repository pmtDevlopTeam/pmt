package com.camelot.pmt.platform.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.DataGrid;
import com.camelot.pmt.common.ExecuteResult;
import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.util.Tree;

public interface OrgService {
	/**
     * 查询部门列表
     * @return ExecuteResult<List<Org>>
     */
	ExecuteResult<List<Tree<Org>>> queryAllOrgs();
    /**
     * 查询单个部门
     * @return ExecuteResult<List<Org>>
     */
    ExecuteResult<Org> queryOrgByOrgId(String orgId);
    
    /**
     * 新增部门
     * @param Org
     * @return ExecuteResult<Org>
     */
    ExecuteResult<String> creatOrg(Org org);

    /**
     * 修改部门
     * @param org
     * @return ExecuteResult<Org>
     */
    ExecuteResult<String> modifyOrgByOrgId(Org org);
    
    /**
     * 删除角色
     * @param org
     * @return
     */
    ExecuteResult<String> deleteOrgByOrgId(Org org);
    /**
     * 分页查询部门列表
     * @param org
     * @return
     */
	ExecuteResult<DataGrid<Org>> queryOrgsByPage(Pager page);
	/**
     * 查询多个子部门
     * @return ExecuteResult<List<Org>>
     */
	ExecuteResult<List<Tree<Org>>> queryOrgAndChildrenById(String orgId);
	/**
	 * 删除多个子部门机构  递归删除
	 */
	ExecuteResult<String> deleteOrgByOrgId(String orgId);
	/**
	 * 组织机构列表详情(关系到用户  即部门负责人)
	 */
	ExecuteResult<List<OrgToUser>> queryOrgsDetail();
	/**
	 * 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 */
	
	ExecuteResult<List<OrgToUser>> queryOrgsDetailByOrgId(String orgId);
	/** 
	 * 组织机构与用户的绑定(关系到用户 )
	 **/
	ExecuteResult<String> addOrgToUser(Org org);
	
	/** 组织机构与用户的绑定 根据orgId查询所有用户(关系到用户 )
	 * @param orgId 
	 * @return List<User>
	 * 
	 **/
	ExecuteResult<List<User>> queryOrgToUserByOrgId(String orgId);
	/** 组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息
	 * @param orgId,orgCode,orgname
	 * @return List<Org>
	 **/
	ExecuteResult<List<Org>> queryOrgByParameters(Org org);
	/** 修改组织机构的状态
	 * @param orgId state
	 * @return JSONObject
	 * 
	 **/
	ExecuteResult<String> modifyOrgByOrgIdAndState(Org org);
	/** 修改组织机构与用户的绑定(关系到用户 )
	 * @param orgId   userIds
	 * @return JSONObject
	 * 
	 **/
	ExecuteResult<String> modifyOrgToUser(Org org);
	
	
	
	
}
