package com.camelot.pmt.project.model;

public class ProjectUserSearchVO {

	private Long projectId;
	private String userName;
	private String userStatus;
	private String userProRoleIds;
	private String roleId;
	private Integer page;
	private Integer size;
	
	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserProRoleIds() {
		return userProRoleIds;
	}

	public void setUserProRoleIds(String userProRoleIds) {
		this.userProRoleIds = userProRoleIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
