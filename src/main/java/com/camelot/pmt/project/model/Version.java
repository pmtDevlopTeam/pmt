package com.camelot.pmt.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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
     * 版本类型
     */
    private String versionType;
    /**
     * 版本名称
     */
    private String versionName;
    /**
     * 版本编号
     */
    private String versionCode;
    /**
     * 版本状态
     */
    private Integer versionStatus;
    /**
     * 版本仓库地址
     */
    private String versionRepositoryUrl;
    /**
     * 版本仓库分支
     */
    private String versionRepositoryBranch;
    /**
     * 版本仓库id
     */
    private String versionRepositoryId;
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String createUserId;

    private Date createTime;

    private String modifyUserId;

    private Date modifyTime;

    private String remarks;

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

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType == null ? null : versionType.trim();
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public Integer getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(Integer versionStatus) {
        this.versionStatus = versionStatus;
    }

    public String getVersionRepositoryUrl() {
        return versionRepositoryUrl;
    }

    public void setVersionRepositoryUrl(String versionRepositoryUrl) {
        this.versionRepositoryUrl = versionRepositoryUrl == null ? null : versionRepositoryUrl.trim();
    }

    public String getVersionRepositoryBranch() {
        return versionRepositoryBranch;
    }

    public void setVersionRepositoryBranch(String versionRepositoryBranch) {
        this.versionRepositoryBranch = versionRepositoryBranch == null ? null : versionRepositoryBranch.trim();
    }

    public String getVersionRepositoryId() {
        return versionRepositoryId;
    }

    public void setVersionRepositoryId(String versionRepositoryId) {
        this.versionRepositoryId = versionRepositoryId == null ? null : versionRepositoryId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}