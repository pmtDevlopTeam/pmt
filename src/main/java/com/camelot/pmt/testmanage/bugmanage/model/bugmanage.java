package com.camelot.pmt.testmanage.bugmanage.model;

import java.util.Date;

public class bugmanage extends bugmanageKey {
    private Long projectId;

    private Long demandId;

    private Long taskId;

    private String designatedId;

    private Long versionId;

    private String bugType;

    private String caseTerminal;

    private String caseEnvironment;

    private String bugLevel;

    private String bugStatus;

    private Long createUserId;

    private Date createTime;

    private Date endTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String delFlag;

    private String stepsReproduce;

    private Date solveTime;

    private Integer solveProgram;

    private Long solveId;

    private Date closeTime;

    private Long closeId;

    private String seriousDegree;

    private String closeReason;

    private Long caseId;

    private String bugDescribe;

    private String closeStauts;

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

    public String getDesignatedId() {
        return designatedId;
    }

    public void setDesignatedId(String designatedId) {
        this.designatedId = designatedId == null ? null : designatedId.trim();
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType == null ? null : bugType.trim();
    }

    public String getCaseTerminal() {
        return caseTerminal;
    }

    public void setCaseTerminal(String caseTerminal) {
        this.caseTerminal = caseTerminal == null ? null : caseTerminal.trim();
    }

    public String getCaseEnvironment() {
        return caseEnvironment;
    }

    public void setCaseEnvironment(String caseEnvironment) {
        this.caseEnvironment = caseEnvironment == null ? null : caseEnvironment.trim();
    }

    public String getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel == null ? null : bugLevel.trim();
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus == null ? null : bugStatus.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    public String getSeriousDegree() {
        return seriousDegree;
    }

    public void setSeriousDegree(String seriousDegree) {
        this.seriousDegree = seriousDegree == null ? null : seriousDegree.trim();
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getBugDescribe() {
        return bugDescribe;
    }

    public void setBugDescribe(String bugDescribe) {
        this.bugDescribe = bugDescribe == null ? null : bugDescribe.trim();
    }

    public String getCloseStauts() {
        return closeStauts;
    }

    public void setCloseStauts(String closeStauts) {
        this.closeStauts = closeStauts == null ? null : closeStauts.trim();
    }
}