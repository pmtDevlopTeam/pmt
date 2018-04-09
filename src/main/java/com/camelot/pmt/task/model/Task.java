package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 任务标识号 
	 */
	private Long taskId;
	/**
	 * 父级任务标识号
	 */
    private Long taskParentId;
    /**
     * 项目标识号
     */
    private Long projectId;
    /**
     * 需求标识号
     */
    private Long demandId;
	/**
	 * 任务名称
	 */
    private String taskName;
    /**
     * 优先级
     */
    private String priority;
    /**
     * 指派人
     */
    private String assignUserName;
    /**
     * 被指派人
     */
    private String beassignUserName;
    /**
     * 任务创建人
     */
    private String createUserName;
    /**
     * 任务完成人
     */
    private String finishUserName;
    /**
     * 任务创建时间
     */
    private Date taskCreateTime;
    /**
     * 任务指派时间
     */
    private Date assignTime;
    /**
     * 任务预计开始时间
     */
    private Date estimateStartTime;
    /**
     * 任务预计结束时间
     */
    private Date estimateEndTime;
    /**
     * 任务实际开始时间
     */
    private Date actualStartTime;
    /**
     * 任务实际结束时间
     */
    private Date actualEndTime;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务进度
     */
    private String taskSpeed;
    /**
     * 任务状态
     */
    private String status;
    /**
     * 任务异常状态
     */
    private String abnormalStatus;
    /**
     * 任务描述
     */
    private String taskDescribe;
    /**
     * 任务预计工时
     */
    private Integer estimateHour;
    /**
     * 任务已消耗工时
     */
    private Integer consumeHour;
    /**
     * 任务剩余工时
     */
    private Integer remainHour;
    /**
     * 任务里程
     */
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