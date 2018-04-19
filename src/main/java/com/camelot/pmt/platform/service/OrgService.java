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
import com.github.pagehelper.PageInfo;

public interface OrgService {
	/**
     * 查询部门列表
     * @return ExecuteResult<List<Org>>
     */
	List<Tree<Org>> queryAllOrgs();
    /**
     * 查询单个部门
     * @return ExecuteResult<List<Org>>
     */
    Org queryOrgByOrgId(String orgId);
    
    /**
     * 新增部门
     * @param Org
     * @return ExecuteResult<Org>
     */
    String addOrg(Org org);

    /**
     * 修改部门
     * @param org
     * @return ExecuteResult<Org>
     */
    String updateOrgByOrgId(Org org);
    
    /**
     * 删除角色
     * @param org
     * @return
     */
    String deleteOrgByOrgId(Org org);
    /**
     * 分页查询部门列表
     * @param org
     * @return
     */
	PageInfo queryOrgsByPage(int pageNum, int pageSize);
	/**
     * 查询多个子部门
     * @return ExecuteResult<List<Org>>
     */
	List<Tree<Org>> queryOrgAndChildrenById(String orgId);
	/**
	 * 删除多个子部门机构  递归删除
	 */
	String deleteOrgByOrgId(String orgId);
	/**
	 * 组织机构列表详情(关系到用户  即部门负责人)
	 */
	List<OrgToUser> queryOrgsDetail();
	/**
	 * 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 */
	
	List<OrgToUser> queryOrgsDetailByOrgId(String orgId);
	/** 
	 * 组织机构与用户的绑定(关系到用户 )
	 **/
	String addOrgToUser(Org org);
	
	/** 组织机构与用户的绑定 根据orgId查询所有用户(关系到用户 )
	 * @param orgId 
	 * @return List<User>
	 * 
	 **/
	List<User> queryOrgToUserByOrgId(String orgId);
	/** 组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息
	 * @param orgId,orgCode,orgname
	 * @return List<Org>
	 **/
	PageInfo queryOrgByParameters(Org org,int pageNum,int pageSize);
	/** 修改组织机构的状态
	 * @param orgId state
	 * @return JSONObject
	 * 
	 **/
	String updateOrgByOrgIdAndState(Org org);
	/** 修改组织机构与用户的绑定(关系到用户 )
	 * @param orgId   userIds
	 * @return JSONObject
	 * 
	 **/
	String updateOrgToUser(Org org);
	
	
	
	
}
