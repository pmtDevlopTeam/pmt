package com.camelot.pmt.project.model;

import java.util.Date;

public class ProjectUser {
    /**
     * id
     */
    private Long id;

    /**
     * 需求id
     */
    private Long reqId;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 预计进项目日期
     */
    private Date preJoinTime;

    /**
     * 实际进项目日期
     */
    private Date realJoinTime;

    /**
     * 预计出项目日期
     */
    private Date preOutTime;

    /**
     * 实际出项目日期
     */
    private Date realOutTime;

    /**
     * 成员在项目状态  01进入，02未进入，03已出 04 暂离
     */
    private String userStatus;

    /**
     * 成员在项目角色
     */
    private Long userProRole;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人id
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 需求id
     * @return req_id 需求id
     */
    public Long getReqId() {
        return reqId;
    }

    /**
     * 需求id
     * @param reqId 需求id
     */
    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    /**
     * 项目id
     * @return project_id 项目id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 预计进项目日期
     * @return pre_join_time 预计进项目日期
     */
    public Date getPreJoinTime() {
        return preJoinTime;
    }

    /**
     * 预计进项目日期
     * @param preJoinTime 预计进项目日期
     */
    public void setPreJoinTime(Date preJoinTime) {
        this.preJoinTime = preJoinTime;
    }

    /**
     * 实际进项目日期
     * @return real_join_time 实际进项目日期
     */
    public Date getRealJoinTime() {
        return realJoinTime;
    }

    /**
     * 实际进项目日期
     * @param realJoinTime 实际进项目日期
     */
    public void setRealJoinTime(Date realJoinTime) {
        this.realJoinTime = realJoinTime;
    }

    /**
     * 预计出项目日期
     * @return pre_out_time 预计出项目日期
     */
    public Date getPreOutTime() {
        return preOutTime;
    }

    /**
     * 预计出项目日期
     * @param preOutTime 预计出项目日期
     */
    public void setPreOutTime(Date preOutTime) {
        this.preOutTime = preOutTime;
    }

    /**
     * 实际出项目日期
     * @return real_out_time 实际出项目日期
     */
    public Date getRealOutTime() {
        return realOutTime;
    }

    /**
     * 实际出项目日期
     * @param realOutTime 实际出项目日期
     */
    public void setRealOutTime(Date realOutTime) {
        this.realOutTime = realOutTime;
    }

    /**
     * 成员在项目状态  01进入，02未进入，03已出 04 暂离
     * @return user_status 成员在项目状态  01进入，02未进入，03已出 04 暂离
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 成员在项目状态  01进入，02未进入，03已出 04 暂离
     * @param userStatus 成员在项目状态  01进入，02未进入，03已出 04 暂离
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 成员在项目角色
     * @return user_pro_role 成员在项目角色
     */
    public Long getUserProRole() {
        return userProRole;
    }

    /**
     * 成员在项目角色
     * @param userProRole 成员在项目角色
     */
    public void setUserProRole(Long userProRole) {
        this.userProRole = userProRole;
    }

    /**
     * 创建人id
     * @return create_user_id 创建人id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * @param createUserId 创建人id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人id
     * @return modify_user_id 修改人id
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 修改人id
     * @param modifyUserId 修改人id
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}