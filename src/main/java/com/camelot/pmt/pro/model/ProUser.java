package com.camelot.pmt.pro.model;

import java.util.Date;

public class ProUser {
    private Integer proUserId;

    private Integer proId;

    private Integer userId;

    private Integer inviterId;

    private Integer removeId;

    private Date preJoinTime;

    private Date realJoinTime;

    private Date preOutTime;

    private Date realOutTime;

    private String userStatus;

    private String userProRole;

    private Integer createUser;

    private Date createTime;

    private Integer modifyUserId;

    private Date modifyTime;

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

    public Integer getProUserId() {
        return proUserId;
    }

    public void setProUserId(Integer proUserId) {
        this.proUserId = proUserId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInviterId() {
        return inviterId;
    }

    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    public Integer getRemoveId() {
        return removeId;
    }

    public void setRemoveId(Integer removeId) {
        this.removeId = removeId;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getUserProRole() {
        return userProRole;
    }

    public void setUserProRole(String userProRole) {
        this.userProRole = userProRole == null ? null : userProRole.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2 == null ? null : column2.trim();
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3 == null ? null : column3.trim();
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4 == null ? null : column4.trim();
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5 == null ? null : column5.trim();
    }
}