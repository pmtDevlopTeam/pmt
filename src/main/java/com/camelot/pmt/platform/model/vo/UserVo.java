package com.camelot.pmt.platform.model.vo;

import java.io.Serializable;

public class UserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5367404691365058832L;
	
	//用户userId
	private String userId;
	
	//用户工号
	private String userJobNum;
	
	//用户名称
	private String userName;
	
	//用户登录账号
	private String loginCode;
	
	//用户所属组织名
	private String orgName;
	
	//用户角色名
	private String roleName;
	
	//用户电话
	private String userPhone;
	
	//用户状态（0启用，1禁用）
	private String state;
	
	//用户角色ID
	private String roleId;

	public UserVo() {
		super();
	}

	public UserVo(String userId, String userJobNum, String userName, String loginCode, String orgName, String roleName,
			String userPhone, String state) {
		super();
		this.userId = userId;
		this.userJobNum = userJobNum;
		this.userName = userName;
		this.loginCode = loginCode;
		this.orgName = orgName;
		this.roleName = roleName;
		this.userPhone = userPhone;
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserJobNum() {
		return userJobNum;
	}

	public void setUserJobNum(String userJobNum) {
		this.userJobNum = userJobNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	

}
