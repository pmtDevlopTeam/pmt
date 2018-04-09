package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer taskId;

    private Integer taskParentId;

    private Integer projectId;

    private Integer demandId;

    private String taskName;

    private String priority;

    private Integer assignUserId;

    private Integer beassignUserId;

    private Integer createUserId;

    private Date taskCreateTime;

    private Date assignTime;

    private Date estimateStartTime;

    private Date estimateEndTime;

    private Date actualStartTime;

    private Date actualEndTime;

    private Integer taskType;

    private String taskSpeed;

    private Integer status;

    private Integer abnormalStatus;

    private Integer finishUserId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(Integer taskParentId) {
        this.taskParentId = taskParentId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
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

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Integer getBeassignUserId() {
        return beassignUserId;
    }

    public void setBeassignUserId(Integer beassignUserId) {
        this.beassignUserId = beassignUserId;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskSpeed() {
        return taskSpeed;
    }

    public void setTaskSpeed(String taskSpeed) {
        this.taskSpeed = taskSpeed == null ? null : taskSpeed.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(Integer abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }

    public Integer getFinishUserId() {
        return finishUserId;
    }

    public void setFinishUserId(Integer finishUserId) {
        this.finishUserId = finishUserId;
    }
    

	public Task(Integer taskId, Integer taskParentId, Integer projectId, Integer demandId, String taskName,
			String priority, Integer assignUserId, Integer beassignUserId, Integer createUserId, Date taskCreateTime,
			Date assignTime, Date estimateStartTime, Date estimateEndTime, Date actualStartTime, Date actualEndTime,
			Integer taskType, String taskSpeed, Integer status, Integer abnormalStatus, Integer finishUserId) {
		super();
		this.taskId = taskId;
		this.taskParentId = taskParentId;
		this.projectId = projectId;
		this.demandId = demandId;
		this.taskName = taskName;
		this.priority = priority;
		this.assignUserId = assignUserId;
		this.beassignUserId = beassignUserId;
		this.createUserId = createUserId;
		this.taskCreateTime = taskCreateTime;
		this.assignTime = assignTime;
		this.estimateStartTime = estimateStartTime;
		this.estimateEndTime = estimateEndTime;
		this.actualStartTime = actualStartTime;
		this.actualEndTime = actualEndTime;
		this.taskType = taskType;
		this.taskSpeed = taskSpeed;
		this.status = status;
		this.abnormalStatus = abnormalStatus;
		this.finishUserId = finishUserId;
	}
	
	public Task() {
		super();
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskParentId=" + taskParentId + ", projectId=" + projectId + ", demandId="
				+ demandId + ", taskName=" + taskName + ", priority=" + priority + ", assignUserId=" + assignUserId
				+ ", beassignUserId=" + beassignUserId + ", createUserId=" + createUserId + ", taskCreateTime="
				+ taskCreateTime + ", assignTime=" + assignTime + ", estimateStartTime=" + estimateStartTime
				+ ", estimateEndTime=" + estimateEndTime + ", actualStartTime=" + actualStartTime + ", actualEndTime="
				+ actualEndTime + ", taskType=" + taskType + ", taskSpeed=" + taskSpeed + ", status=" + status
				+ ", abnormalStatus=" + abnormalStatus + ", finishUserId=" + finishUserId + "]";
	}
    
}