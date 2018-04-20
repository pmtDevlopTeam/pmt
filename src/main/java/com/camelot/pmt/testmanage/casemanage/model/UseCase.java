package com.camelot.pmt.testmanage.casemanage.model;

import java.util.Date;
import java.util.List;

public class UseCase {
    private Long id;
    // 项目id
    private Long projectId;
    // 需求id
    private Long demandId;
    // 标题
    private String caseTitle;
    // 用例类型(字典表ID)
    private String caseType;
    // 相关版本
    private Long versionId;
    // 优先级
    private String caseLevel;
    // 用例描述
    private String description;
    // 执行人ID
    private String executeUserId;
    // 执行时间
    private Date executeTime;
    // 用例状态(0:正常；1:被阻塞；2：研究中)
    private String caseStatus;
    // 结果(0:通过；1：失败)
    private String result;
    // 创建人时间
    private String createUserId;
    // 创建时间
    private Date createTime;
    // 修改人id
    private String modifyUserId;
    // 修改时间
    private Date modifyTime;
    // 删除标记(0:正常；1：删除)
    private String delFlag;
    // 前置条件
    private String precondition;
    // 适用阶段
    private String applyPhase;
    // 用例步骤
    private List<UseCaseProcedure> procedure;

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

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle == null ? null : caseTitle.trim();
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType == null ? null : caseType.trim();
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel == null ? null : caseLevel.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus == null ? null : caseStatus.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public List<UseCaseProcedure> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<UseCaseProcedure> procedure) {
        this.procedure = procedure;
    }

    public String getExecuteUserId() {
        return executeUserId;
    }

    public void setExecuteUserId(String executeUserId) {
        this.executeUserId = executeUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition;
    }

    public String getApplyPhase() {
        return applyPhase;
    }

    public void setApplyPhase(String applyPhase) {
        this.applyPhase = applyPhase;
    }

}