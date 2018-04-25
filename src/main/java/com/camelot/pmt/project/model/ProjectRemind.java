package com.camelot.pmt.project.model;

import java.util.Date;

public class ProjectRemind {
    /**
     * 主键
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 提醒类型
     */
    private String remindType;

    /**
     * 提醒状态
     */
    private String remindStatus;

    /**
     * 提醒频次
     */
    private String remindFrequency;

    /**
     * 成员在项目角色
     */
    private String projectRoleId;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 主键
     * 
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * 
     * @param id
     *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 项目id
     * 
     * @return project_id 项目id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * 
     * @param projectId
     *            项目id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 提醒类型
     * 
     * @return remind_type 提醒类型
     */
    public String getRemindType() {
        return remindType;
    }

    /**
     * 提醒类型
     * 
     * @param remindType
     *            提醒类型
     */
    public void setRemindType(String remindType) {
        this.remindType = remindType == null ? null : remindType.trim();
    }

    /**
     * 提醒状态
     * 
     * @return remind_status 提醒状态
     */
    public String getRemindStatus() {
        return remindStatus;
    }

    /**
     * 提醒状态
     * 
     * @param remindStatus
     *            提醒状态
     */
    public void setRemindStatus(String remindStatus) {
        this.remindStatus = remindStatus == null ? null : remindStatus.trim();
    }

    /**
     * 提醒频次
     * 
     * @return remind_frequency 提醒频次
     */
    public String getRemindFrequency() {
        return remindFrequency;
    }

    /**
     * 提醒频次
     * 
     * @param remindFrequency
     *            提醒频次
     */
    public void setRemindFrequency(String remindFrequency) {
        this.remindFrequency = remindFrequency == null ? null : remindFrequency.trim();
    }

    /**
     * 成员在项目角色
     * 
     * @return project_role_id 成员在项目角色
     */
    public String getProjectRoleId() {
        return projectRoleId;
    }

    /**
     * 成员在项目角色
     * 
     * @param projectRoleId
     *            成员在项目角色
     */
    public void setProjectRoleId(String projectRoleId) {
        this.projectRoleId = projectRoleId == null ? null : projectRoleId.trim();
    }

    /**
     * 创建人id
     * 
     * @return create_user_id 创建人id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * 
     * @param createUserId
     *            创建人id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * 创建时间
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * 
     * @param createTime
     *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}