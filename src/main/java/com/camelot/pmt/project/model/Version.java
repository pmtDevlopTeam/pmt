package com.camelot.pmt.project.model;

import java.util.Date;

public class Version {
    /**
     * 版本号_id
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 版本编号
     */
    private String version;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 版本类型
     */
    private String versionType;

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
     * 版本号_id
     * @return id 版本号_id
     */
    public Long getId() {
        return id;
    }

    /**
     * 版本号_id
     * @param id 版本号_id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 版本编号
     * @return version 版本编号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 版本编号
     * @param version 版本编号
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 版本名称
     * @return version_name 版本名称
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 版本名称
     * @param versionName 版本名称
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    /**
     * 版本类型
     * @return version_type 版本类型
     */
    public String getVersionType() {
        return versionType;
    }

    /**
     * 版本类型
     * @param versionType 版本类型
     */
    public void setVersionType(String versionType) {
        this.versionType = versionType == null ? null : versionType.trim();
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