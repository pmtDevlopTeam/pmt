package com.camelot.pmt.platform.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.DataGrid;
import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.common.Pager;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgToUser;
import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.util.Tree;

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
	ExecuteResult<List<User>> queryOrgToUser(String orgId);
	
	
	
}
