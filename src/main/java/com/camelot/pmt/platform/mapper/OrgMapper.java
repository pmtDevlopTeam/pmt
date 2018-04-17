package com.camelot.pmt.platform.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.camelot.pmt.common.Pager;
import com.camelot.pmt.platform.model.Org;
import com.camelot.pmt.platform.model.OrgAndUser;
import com.camelot.pmt.platform.model.OrgToUser;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-04-08
 */
public interface OrgMapper {
	
	/**
	 * 增加一个部门
	 * @param org
	 * @return 
	 */
	int createOrg(Org org);
	
	/**
	 * 根据部门id 删除一个部门
	 * @param orgId
	 * @return
	 */
    int deleteOrgByOrgId(String orgId);
	
	/**
	 * 根据部门id 修改一个部门
	 * @param Org
	 * @return
	 */
    int modifyOrgByOrgId(Org org);

    /**
     * 根据部门id 查询一个部门
     * @param orgId
     * @return
     */
    Org queryOrgByOrgId(String orgId);
    
    /**
     * 查询全部部门菜单树
     * @param 
     * @return
     */
    List<Org> queryAllOrg();
    /**
     * 分页查询部门列表
     * @param org
     * @return
     */
	List<Org> findOrgsByPage(Pager page);
	/**
     * 查询部门表总条数
     * @param org
     * @return
     */
	Long queryCount();
	/**
     * 根据部门parentId 查询子部门
     * @param orgId
     * @return
     */
	List<Org> selectOrgChildrenByParentId(String parentId);
	/**
     * 根据部门parentId 递归删除子部门
     * @param orgId
     * @return
     */
	void deleteOrgByOrgParentId(String parentId);
	/**
	 * 组织机构列表详情和查看(关系到用户  即部门负责人)
	 */
	List<OrgToUser> selectOrgsDetail();
	/** 
	 * 组织机构   根据orgId查看详情(关系到用户  即部门负责人)
	 **/
	List<OrgToUser> selectOrgsDetailByOrgId(String orgId);
	/** 
	 * 组织机构与用户的绑定(关系到用户 )
	 **/
	int createOrgToUser(Org org);
	/** 
	 * 删除组织机构与用户的已绑定(关系到用户 )
	 **/
	int deleteOrgByUserIdAndOrgId(@Param("userId") String userId, @Param("orgId") String orgId);
	/** 
	 * 查询组织机构与用户的已绑定(关系到用户 )
	 * @return 
	 **/
	OrgAndUser selectOrgAndUserByOrgIdAndUserId(@Param("userId") String userId, @Param("orgId") String orgId);
	 /** 组织机构与用户的绑定 根据orgId查询所有用户(关系到用户 )
	  * @param orgId 
	  * @return List<User>
	 **/
	List<OrgAndUser> selectUsersByOrgId(String orgId);
	/** 组织机构  根据orgId,orgCode,orgname 多条件模糊查询所有部门信息
	 * @param orgId,orgCode,orgname
	 * @return List<Org>
	 **/
	List<Org> queryOrgByParameters(Org org);
	/** 修改组织机构的状态
	 * @param orgId state
	 * @return JSONObject
	 * 
	 **/
	int modifyOrgByOrgIdAndState(Org org);
	/** 
	 * 删除组织机构与用户的已绑定(关系到用户 )
	 **/
	int deleteOrgToUserByOrgId(String orgId);
    
    
}
