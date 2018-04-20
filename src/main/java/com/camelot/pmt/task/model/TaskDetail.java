package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDetail extends TaskLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String taskName;

    private Long taskParentId;

    private Long projectId;

    private Long demandId;

    private String priority;

    private String assignUserId;

    private String beassignUserId;

    private Date assignTime;

    private Date estimateStartTime;

    private Date estimateEndTime;

    private Date actualStartTime;

    private Date actualEndTime;

    private String taskType;

    private String taskSpeed;

    private String status;

    private String abnormalStatus;

    private String taskDescribe;

    private String abnormalDescribe;

    private Integer estimateHour;

    private Integer consumeHour;

    private Integer remainHour;

    private String taskMileage;

    private String createUserId;

    private String modifyUserId;

    private Date createTime;

    private Date modifyTime;

    private Integer warningHour;

    private Integer warningStatus;

    private String comment;

    private String filepath;

    private String filename;

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

    private List<Task> children = new ArrayList<Task>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(Long taskParentId) {
        this.taskParentId = taskParentId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(String assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getBeassignUserId() {
        return beassignUserId;
    }

    public void setBeassignUserId(String beassignUserId) {
        this.beassignUserId = beassignUserId;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Date getEstimateStartTime() {
        return estimateStartTime;
    }

    public void setEstimateStartTime(Date estimateStartTime) {
        this.estimateStartTime = estimateStartTime;
    }

    public Date getEstimateEndTime() {
        return estimateEndTime;
    }

    public void setEstimateEndTime(Date estimateEndTime) {
        this.estimateEndTime = estimateEndTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskSpeed() {
        return taskSpeed;
    }

    public void setTaskSpeed(String taskSpeed) {
        this.taskSpeed = taskSpeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(String abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getAbnormalDescribe() {
        return abnormalDescribe;
    }

    public void setAbnormalDescribe(String abnormalDescribe) {
        this.abnormalDescribe = abnormalDescribe;
    }

    public Integer getEstimateHour() {
        return estimateHour;
    }

    public void setEstimateHour(Integer estimateHour) {
        this.estimateHour = estimateHour;
    }

    public Integer getConsumeHour() {
        return consumeHour;
    }

    public void setConsumeHour(Integer consumeHour) {
        this.consumeHour = consumeHour;
    }

    public Integer getRemainHour() {
        return remainHour;
    }

    public void setRemainHour(Integer remainHour) {
        this.remainHour = remainHour;
    }

    public String getTaskMileage() {
        return taskMileage;
    }

    public void setTaskMileage(String taskMileage) {
        this.taskMileage = taskMileage;
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

    public Integer getWarningHour() {
        return warningHour;
    }

    public void setWarningHour(Integer warningHour) {
        this.warningHour = warningHour;
    }

    public Integer getWarningStatus() {
        return warningStatus;
    }

    public void setWarningStatus(Integer warningStatus) {
        this.warningStatus = warningStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public TaskDetail(Long id, String taskName, Long taskParentId, Long projectId, Long demandId, String priority,
            String assignUserId, String beassignUserId, Date assignTime, Date estimateStartTime, Date estimateEndTime,
            Date actualStartTime, Date actualEndTime, String taskType, String taskSpeed, String status,
            String abnormalStatus, String taskDescribe, String abnormalDescribe, Integer estimateHour,
            Integer consumeHour, Integer remainHour, String taskMileage, String createUserId, String modifyUserId,
            Date createTime, Date modifyTime, Integer warningHour, Integer warningStatus, String comment,
            String filepath, String filename, String column1, String column2, String column3, String column4,
            String column5, List<Task> children) {
        super();
        this.id = id;
        this.taskName = taskName;
        this.taskParentId = taskParentId;
        this.projectId = projectId;
        this.demandId = demandId;
        this.priority = priority;
        this.assignUserId = assignUserId;
        this.beassignUserId = beassignUserId;
        this.assignTime = assignTime;
        this.estimateStartTime = estimateStartTime;
        this.estimateEndTime = estimateEndTime;
        this.actualStartTime = actualStartTime;
        this.actualEndTime = actualEndTime;
        this.taskType = taskType;
        this.taskSpeed = taskSpeed;
        this.status = status;
        this.abnormalStatus = abnormalStatus;
        this.taskDescribe = taskDescribe;
        this.abnormalDescribe = abnormalDescribe;
        this.estimateHour = estimateHour;
        this.consumeHour = consumeHour;
        this.remainHour = remainHour;
        this.taskMileage = taskMileage;
        this.createUserId = createUserId;
        this.modifyUserId = modifyUserId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.warningHour = warningHour;
        this.warningStatus = warningStatus;
        this.comment = comment;
        this.filepath = filepath;
        this.filename = filename;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.children = children;
    }

    public TaskDetail() {
        super();
    }

    @Override
    public String toString() {
        return "TaskDetail [id=" + id + ", taskName=" + taskName + ", taskParentId=" + taskParentId + ", projectId="
                + projectId + ", demandId=" + demandId + ", priority=" + priority + ", assignUserId=" + assignUserId
                + ", beassignUserId=" + beassignUserId + ", assignTime=" + assignTime + ", estimateStartTime="
                + estimateStartTime + ", estimateEndTime=" + estimateEndTime + ", actualStartTime=" + actualStartTime
                + ", actualEndTime=" + actualEndTime + ", taskType=" + taskType + ", taskSpeed=" + taskSpeed
                + ", status=" + status + ", abnormalStatus=" + abnormalStatus + ", taskDescribe=" + taskDescribe
                + ", abnormalDescribe=" + abnormalDescribe + ", estimateHour=" + estimateHour + ", consumeHour="
                + consumeHour + ", remainHour=" + remainHour + ", taskMileage=" + taskMileage + ", createUserId="
                + createUserId + ", modifyUserId=" + modifyUserId + ", createTime=" + createTime + ", modifyTime="
                + modifyTime + ", warningHour=" + warningHour + ", warningStatus=" + warningStatus + ", comment="
                + comment + ", filepath=" + filepath + ", filename=" + filename + ", column1=" + column1 + ", column2="
                + column2 + ", column3=" + column3 + ", column4=" + column4 + ", column5=" + column5 + ", children="
                + children + "]";
    }

}
