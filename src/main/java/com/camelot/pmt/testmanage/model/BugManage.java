package com.camelot.pmt.testmanage.model;

import java.util.Date;

public class BugManage {
    private Long id;

    private Long projectId;

    private Long demandId;

    private Long taskId;

    private Long designatedId;

    private Long versionId;

    private String bugTitle;

    private String bugType;

    private String explorer;

    private String operatingSystem;

    private Byte bugLevel;

    private Byte bugStatus;

    private Long createId;

    private Date createTime;

    private Date endTime;

    private Long updateId;

    private Date updateTime;

    private String delFlag;

    private String stepsReproduce;

    private String attachName;

    private Date solveTime;

    private Integer solveProgram;

    private Long solveId;

    private Long responsibleId;

    private Date closeTime;

    private Long closeId;

    private String attachUrl;

    private Integer seriousDegree;

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

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getDesignatedId() {
        return designatedId;
    }

    public void setDesignatedId(Long designatedId) {
        this.designatedId = designatedId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle == null ? null : bugTitle.trim();
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType == null ? null : bugType.trim();
    }

    public String getExplorer() {
        return explorer;
    }

    public void setExplorer(String explorer) {
        this.explorer = explorer == null ? null : explorer.trim();
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem == null ? null : operatingSystem.trim();
    }

    public Byte getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(Byte bugLevel) {
        this.bugLevel = bugLevel;
    }

    public Byte getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(Byte bugStatus) {
        this.bugStatus = bugStatus;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getStepsReproduce() {
        return stepsReproduce;
    }

    public void setStepsReproduce(String stepsReproduce) {
        this.stepsReproduce = stepsReproduce == null ? null : stepsReproduce.trim();
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName == null ? null : attachName.trim();
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public Integer getSolveProgram() {
        return solveProgram;
    }

    public void setSolveProgram(Integer solveProgram) {
        this.solveProgram = solveProgram;
    }

    public Long getSolveId() {
        return solveId;
    }

    public void setSolveId(Long solveId) {
        this.solveId = solveId;
    }

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Long getCloseId() {
        return closeId;
    }

    public void setCloseId(Long closeId) {
        this.closeId = closeId;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl == null ? null : attachUrl.trim();
    }

    public Integer getSeriousDegree() {
        return seriousDegree;
    }

    public void setSeriousDegree(Integer seriousDegree) {
        this.seriousDegree = seriousDegree;
    }
}