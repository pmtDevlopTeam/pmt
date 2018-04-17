package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Org implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	/**
 * 组织机构实体
 */
	/**
     * id
     */
    private Integer id;
    /**
     * 部门唯一11位id
     */
    private String orgId;
    /**
     * 顶级部门  0为顶级部门
     */
    private String parentId;
    /**
     * 部门名称
     */
    private String orgname;
    /**
     * 部门状态
     */
    private String state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    
    /**
     * 排序号  默认值1000
     */
    private Integer sortNum;
    /**
     * 部门编号
     */
    private String orgCode;
    
    /**
     * 修改人
     */
    private String modifyUserId;
    /**
     * 创建人
     */
    private String creatUserId;
    /**
     * 用户id 数组
     */
    private String[] userIds;
    /**
     * 用户id 数组
     */
    private String userId;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
    public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String getCreatUserId() {
		return creatUserId;
	}

	public void setCreatUserId(String creatUserId) {
		this.creatUserId = creatUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

private List<Org> orgList;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<Org> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}

	
	
}
