package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

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

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", taskParentId=" + taskParentId + ", projectId=" + projectId + ", demandId="
                + demandId + ", taskName=" + taskName + ", priority=" + priority + ", assignUserName=" + assignUserName
                + ", beassignUserName=" + beassignUserName + ", createUserName=" + createUserName + ", finishUserName="
                + finishUserName + ", taskCreateTime=" + taskCreateTime + ", assignTime=" + assignTime
                + ", estimateStartTime=" + estimateStartTime + ", estimateEndTime=" + estimateEndTime
                + ", actualStartTime=" + actualStartTime + ", actualEndTime=" + actualEndTime + ", taskType=" + taskType
                + ", taskSpeed=" + taskSpeed + ", status=" + status + ", abnormalStatus=" + abnormalStatus
                + ", taskDescribe=" + taskDescribe + ", estimateHour=" + estimateHour + ", consumeHour=" + consumeHour
                + ", remainHour=" + remainHour + ", taskMileage=" + taskMileage + ", column1=" + column1 + ", column2="
                + column2 + ", column3=" + column3 + ", column4=" + column4 + ", column5=" + column5 + "]";
    }

    public Task(Long taskId, Long taskParentId, Long projectId, Long demandId, String taskName, String priority,
            String assignUserName, String beassignUserName, String createUserName, String finishUserName,
            Date taskCreateTime, Date assignTime, Date estimateStartTime, Date estimateEndTime, Date actualStartTime,
            Date actualEndTime, String taskType, String taskSpeed, String status, String abnormalStatus,
            String taskDescribe, Integer estimateHour, Integer consumeHour, Integer remainHour, String taskMileage,
            String column1, String column2, String column3, String column4, String column5) {
        super();
        this.taskId = taskId;
        this.taskParentId = taskParentId;
        this.projectId = projectId;
        this.demandId = demandId;
        this.taskName = taskName;
        this.priority = priority;
        this.assignUserName = assignUserName;
        this.beassignUserName = beassignUserName;
        this.createUserName = createUserName;
        this.finishUserName = finishUserName;
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
        this.taskDescribe = taskDescribe;
        this.estimateHour = estimateHour;
        this.consumeHour = consumeHour;
        this.remainHour = remainHour;
        this.taskMileage = taskMileage;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
    }

    public Task() {
        super();
    }

}