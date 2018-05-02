package com.camelot.pmt.project.model;

import java.util.Date;

public class RemindContentChild {
    /**
     * 主键
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 内容表id
     */
    private Long contentId;

    /**
     * 编码名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 方法url
     */
    private String methodUrl;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 延期提醒天数
     */
    private Integer delayRemindDays;

    /**
     * 备注
     */
    private Integer remark;

    /**
     * 角色id
     */
    private String projectRoleId;
    public String getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(String projectRoleId) {
        this.projectRoleId = projectRoleId;
    }

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
     * 内容表id
     * 
     * @return content_id 内容表id
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * 内容表id
     * 
     * @param contentId
     *            内容表id
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * 编码名称
     * 
     * @return name 编码名称
     */
    public String getName() {
        return name;
    }

    /**
     * 编码名称
     * 
     * @param name
     *            编码名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 编码
     * 
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * 
     * @param code
     *            编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 方法url
     * 
     * @return method_url 方法url
     */
    public String getMethodUrl() {
        return methodUrl;
    }

    /**
     * 方法url
     * 
     * @param methodUrl
     *            方法url
     */
    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl == null ? null : methodUrl.trim();
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

    /**
     * 延期提醒天数
     * 
     * @return delay_remind_days 延期提醒天数
     */
    public Integer getDelayRemindDays() {
        return delayRemindDays;
    }

    /**
     * 延期提醒天数
     * 
     * @param delayRemindDays
     *            延期提醒天数
     */
    public void setDelayRemindDays(Integer delayRemindDays) {
        this.delayRemindDays = delayRemindDays;
    }

    /**
     * 备注
     * 
     * @return remark 备注
     */
    public Integer getRemark() {
        return remark;
    }

    /**
     * 备注
     * 
     * @param remark
     *            备注
     */
    public void setRemark(Integer remark) {
        this.remark = remark;
    }
}