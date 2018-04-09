package com.camelot.pmt.task.model;

import java.util.Date;

public class Task {
    private Long taskId;

    private Long taskParentId;

    private Long projectId;

    private Long demandId;

    private String taskName;

    private String priority;

    private String assignUserName;

    private String beassignUserName;

    private String createUserName;

    private String finishUserName;

    private Date taskCreateTime;

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

    private Integer estimateHour;

    private Integer consumeHour;

    private Integer remainHour;

    private String taskMileage;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getAssignUserName() {
        return assignUserName;
    }

    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName == null ? null : assignUserName.trim();
    }

    public String getBeassignUserName() {
        return beassignUserName;
    }

    public void setBeassignUserName(String beassignUserName) {
        this.beassignUserName = beassignUserName == null ? null : beassignUserName.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getFinishUserName() {
        return finishUserName;
    }

    public void setFinishUserName(String finishUserName) {
        this.finishUserName = finishUserName == null ? null : finishUserName.trim();
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
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
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskSpeed() {
        return taskSpeed;
    }

    public void setTaskSpeed(String taskSpeed) {
        this.taskSpeed = taskSpeed == null ? null : taskSpeed.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(String abnormalStatus) {
        this.abnormalStatus = abnormalStatus == null ? null : abnormalStatus.trim();
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe == null ? null : taskDescribe.trim();
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
        this.taskMileage = taskMileage == null ? null : taskMileage.trim();
    }
}