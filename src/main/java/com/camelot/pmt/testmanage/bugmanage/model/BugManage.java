package com.camelot.pmt.testmanage.bugmanage.model;

import javax.xml.bind.annotation.XmlElement;

public class BugManage {
	@XmlElement(name = "default")
    private Long id;
	@XmlElement(name = "default")
    private String bugNo;
    // 标题
    @XmlElement(name = "bug标题")
    private String bugTitle;
    // 项目ID
    @XmlElement(name = "项目")
    private Long projectId;
    // 相关需求ID
    @XmlElement(name = "需求")
    private Long demandId;
    // 相关任务ID
    @XmlElement(name = "任务")
    private Long taskId;
    // 一级需求ID
    @XmlElement(name = "一级需求")
    private Long demand1Id;
    // 指派人
    @XmlElement(name = "指派给")
    private String designatedId;
    // 影响版本ID
    @XmlElement(name = "影响版本")
    private Long versionId;
    // bug类型
    @XmlElement(name = "bug类型")
    private String bugType;
    // 测试终端
    @XmlElement(name = "测试终端")
    private String caseTerminal;
    // 测试环境
    @XmlElement(name = "测试环境")
    private String caseEnvironment;
    // bug级别（优先级）
    @XmlElement(name = "bug级别")
    private String bugLevel;
    // 状态（0未确认 1已确认 2已解决 3已关闭 4已撤销）
    @XmlElement(name = "bug状态")
    private String bugStatus;
    // 创建者
    @XmlElement(name = "default")
    private String createUserId;
    // 创建时间
    @XmlElement(name = "default")
    private String createTime;
    // 截止日期
    @XmlElement(name = "截至日期")
    private String endTime;
    // 更新者
    @XmlElement(name = "default")
    private String modifyUserId;
    // 更新时间
    @XmlElement(name = "default")
    private String modifyTime;
    // 删除标记
    @XmlElement(name = "default")
    private String delFlag;
    // 重现步骤
    @XmlElement(name = "重现步骤")
    private String stepsReproduce;
    // 解决时间
    @XmlElement(name = "解决时间")
    private String solveTime;
    // 解决方案
    @XmlElement(name = "解决方案")
    private Integer solveProgram;
    // 解决人
    @XmlElement(name = "解决人")
    private String solveId;
    // 关闭时间
    @XmlElement(name = "关闭时间")
    private String closeTime;
    // 关闭人
    @XmlElement(name = "关闭人")
    private String closeId;
    // 严重程度
    @XmlElement(name = "严重程度")
    private String seriousDegree;
    // 关闭原因
    @XmlElement(name = "关闭原因")
    private String closeReason;
    // 用例ID
    @XmlElement(name = "用例")
    private Long caseId;
    // 备注
    @XmlElement(name = "备注")
    private String bugDescribe;
    // 关闭状态（正常，不正常）
    @XmlElement(name = "default")
    private String closeStauts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
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

    public String getDesignatedId() {
        return designatedId;
    }

    public void setDesignatedId(String designatedId) {
        this.designatedId = designatedId;
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
        this.bugType = bugType;
    }

    public String getCaseTerminal() {
        return caseTerminal;
    }

    public void setCaseTerminal(String caseTerminal) {
        this.caseTerminal = caseTerminal;
    }

    public String getCaseEnvironment() {
        return caseEnvironment;
    }

    public void setCaseEnvironment(String caseEnvironment) {
        this.caseEnvironment = caseEnvironment;
    }

    public String getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getStepsReproduce() {
        return stepsReproduce;
    }

    public void setStepsReproduce(String stepsReproduce) {
        this.stepsReproduce = stepsReproduce;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public Integer getSolveProgram() {
        return solveProgram;
    }

    public void setSolveProgram(Integer solveProgram) {
        this.solveProgram = solveProgram;
    }

    public String getSolveId() {
        return solveId;
    }

    public void setSolveId(String solveId) {
        this.solveId = solveId;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseId() {
        return closeId;
    }

    public void setCloseId(String closeId) {
        this.closeId = closeId;
    }

    public String getSeriousDegree() {
        return seriousDegree;
    }

    public void setSeriousDegree(String seriousDegree) {
        this.seriousDegree = seriousDegree;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
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
        this.bugDescribe = bugDescribe;
    }

    public String getCloseStauts() {
        return closeStauts;
    }

    public void setCloseStauts(String closeStauts) {
        this.closeStauts = closeStauts;
    }

    public String getBugNo() {
        return bugNo;
    }

    public void setBugNo(String bugNo) {
        this.bugNo = bugNo;
    }

    public Long getDemand1Id() {
        return demand1Id;
    }

    public void setDemand1Id(Long demand1Id) {
        this.demand1Id = demand1Id;
    }

    @Override
    public String toString() {
        return "BugManage [id=" + id + ", bugNo=" + bugNo + ", bugTitle=" + bugTitle + ", projectId=" + projectId
                + ", demandId=" + demandId + ", taskId=" + taskId + ", demand1Id=" + demand1Id + ", designatedId="
                + designatedId + ", versionId=" + versionId + ", bugType=" + bugType + ", caseTerminal=" + caseTerminal
                + ", caseEnvironment=" + caseEnvironment + ", bugLevel=" + bugLevel + ", bugStatus=" + bugStatus
                + ", createUserId=" + createUserId + ", createTime=" + createTime + ", endTime=" + endTime
                + ", modifyUserId=" + modifyUserId + ", modifyTime=" + modifyTime + ", delFlag=" + delFlag
                + ", stepsReproduce=" + stepsReproduce + ", solveTime=" + solveTime + ", solveProgram=" + solveProgram
                + ", solveId=" + solveId + ", closeTime=" + closeTime + ", closeId=" + closeId + ", seriousDegree="
                + seriousDegree + ", closeReason=" + closeReason + ", caseId=" + caseId + ", bugDescribe=" + bugDescribe
                + ", closeStauts=" + closeStauts + "]";
    }

}