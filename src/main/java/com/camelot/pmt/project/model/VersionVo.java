package com.camelot.pmt.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Package: com.camelot.pmt.project.model
 * @ClassName: VersionVo
 * @Description: 接收version传递信息
 * @author: xueyj
 * @date: 2018-04-16 16:09
 */
public class VersionVo {
    /**
     * 版本号_id
     */
    private Long id;
    /**
     * 版本名称
     */
    private String versionName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 版本类型
     */
    private String versionType;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 版本编号
     */
    private String versionCode;
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
    /**
     * id
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * 
     * @param id
     *            id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 版本名称
     * 
     * @return version_name 版本名称
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 版本名称
     * 
     * @param versionName
     *            版本名称
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    /**
     * 开始时间
     * 
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * 
     * @param startTime
     *            开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * 
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * 
     * @param endTime
     *            结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 版本类型
     * 
     * @return version_type 版本类型
     */
    public String getVersionType() {
        return versionType;
    }

    /**
     * 版本类型
     * 
     * @param versionType
     *            版本类型
     */
    public void setVersionType(String versionType) {
        this.versionType = versionType == null ? null : versionType.trim();
    }

    /**
     * 备注
     * 
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * 
     * @param remarks
     *            备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 版本编号
     *
     * @return version 版本编号
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 版本编号
     *
     * @param versionCode
     *            版本编号
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
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
}
