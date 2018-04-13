package com.camelot.pmt.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjectUserShow {

	private Long id;
	private Long projectId;
	private String userId;
	private String userName;
	private int preManHour;
	private String userStatus;
	private Long userProRole;
	private String userProRoleName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date preJoinTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date realJoinTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date preOutTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date realOutTime;
	private List<String> userRoleNames = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPreManHour() {
		return preManHour;
	}

	public void setPreManHour(int preManHour) {
		this.preManHour = preManHour;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Long getUserProRole() {
		return userProRole;
	}

	public void setUserProRole(Long userProRole) {
		this.userProRole = userProRole;
	}

	public String getUserProRoleName() {
		return userProRoleName;
	}

	public void setUserProRoleName(String userProRoleName) {
		this.userProRoleName = userProRoleName;
	}

	public Date getPreJoinTime() {
		return preJoinTime;
	}

	public void setPreJoinTime(Date preJoinTime) {
		this.preJoinTime = preJoinTime;
	}

	public Date getRealJoinTime() {
		return realJoinTime;
	}

	public void setRealJoinTime(Date realJoinTime) {
		this.realJoinTime = realJoinTime;
	}

	public Date getPreOutTime() {
		return preOutTime;
	}

	public void setPreOutTime(Date preOutTime) {
		this.preOutTime = preOutTime;
	}

	public Date getRealOutTime() {
		return realOutTime;
	}

	public void setRealOutTime(Date realOutTime) {
		this.realOutTime = realOutTime;
	}

	public List<String> getUserRoleNames() {
		return userRoleNames;
	}

	public void setUserRoleNames(List<String> userRoleNames) {
		this.userRoleNames = userRoleNames;
	}

}
