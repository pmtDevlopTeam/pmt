package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8529564394952754806L;
	

	/**
	 * 用户的索引ID，仅用户查询等业务操作
	 */
	private Long id;
	/**
	 * 用户唯一32位UUID
	 */
	private String userId;
	/**
	 * 登陆账号
	 */
	private String loginCode;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 用户名称
	 */
	private String username;
	/**
	 *用户状态  0（默认）启用 1 停用 2 锁定'
	 */
	private String state;
	/**
	 * 用户创建时间
	 */
	private Date createTime;
	/**
	 * 用户修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 创建人ID
	 */
	private String createUserId;
	
	/**
	 * 修改人ID
	 */
	private String modifyUserId;

	/**
	 * 用户工号
	 */
	private String userJobNum;
	
	/**
	 * 用户手机号
	 */
	private String userPhone;
	
	/**
	 * 用户邮箱
	 */
	private String userMail;
	
	/**
	 * 部门唯一32位UUID
	 */
	private String orgId;
	
	/**
	 * 角色唯一32位UUID
	 */
	private String roleId;
	
	/**
	 * 角色唯一32位UUID,多个角色ID绑定
	 */
	private String[] roleIds;

	public User() {
		super();
	}

	public User(Long id, String userId, String loginCode, String password, String username, String state,
			Date createTime, Date modifyTime, String createUserId, String modifyUserId, String userJobNum,
			String userPhone, String userMail, String orgId, String roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.loginCode = loginCode;
		this.password = password;
		this.username = username;
		this.state = state;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createUserId = createUserId;
		this.modifyUserId = modifyUserId;
		this.userJobNum = userJobNum;
		this.userPhone = userPhone;
		this.userMail = userMail;
		this.orgId = orgId;
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getUserJobNum() {
		return userJobNum;
	}

	public void setUserJobNum(String userJobNum) {
		this.userJobNum = userJobNum;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	

}
