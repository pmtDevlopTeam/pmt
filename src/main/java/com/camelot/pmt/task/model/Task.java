package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements Serializable{
	/**
	 * 任务ID（主键）
	 */
    private Long id;
    /**
     * 任务编号
     */
    private String taskNum;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 父级任务ID 
     */
    private Long taskParentId;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 需求ID
     */
    private Long demandId;
    /**
     * 优先级
     */
    private String priority;
    /**
     * 指派人ID
     */
    private String assignUserId;
    /**
     * 负责人ID
     */
    private String beassignUserId;
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
     * 任务状态（0 未开始 1 正在进行 2 已完成 3 延期 4 关闭）
     */
    private String status;
    /**
     * 节点等级（1，2，3，4）
     */
    private String nodeLv;
    /**
     * 任务描述
     */
    private String taskDescribe;
    /**
     * 需求是否变更
     */
    private String demandChange;
    /**
     * 延期原因
     */
    private String delayDescribe;
    /**
     * 任务预计工时
     */
    private Long estimateHour;
    /**
     * 任务实际工时
     */
    private Long infactHour;
    /**
     * 任务里程
     */
    private String taskMileage;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 修改人ID
     */
    private String modifyUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 子任务集合
     */
    private List<Task> children = new ArrayList<Task>();

    public List<Task> getChildren() {
        return children;
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum == null ? null : taskNum.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
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
        this.priority = priority == null ? null : priority.trim();
    }

    public String getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(String assignUserId) {
        this.assignUserId = assignUserId == null ? null : assignUserId.trim();
    }

    public String getBeassignUserId() {
        return beassignUserId;
    }

    public void setBeassignUserId(String beassignUserId) {
        this.beassignUserId = beassignUserId == null ? null : beassignUserId.trim();
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

    public String getNodeLv() {
        return nodeLv;
    }

    public void setNodeLv(String nodeLv) {
        this.nodeLv = nodeLv == null ? null : nodeLv.trim();
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe == null ? null : taskDescribe.trim();
    }

    public String getDemandChange() {
        return demandChange;
    }

    public void setDemandChange(String demandChange) {
        this.demandChange = demandChange == null ? null : demandChange.trim();
    }

    public String getDelayDescribe() {
        return delayDescribe;
    }

    public void setDelayDescribe(String delayDescribe) {
        this.delayDescribe = delayDescribe == null ? null : delayDescribe.trim();
    }

    public Long getEstimateHour() {
        return estimateHour;
    }

    public void setEstimateHour(Long estimateHour) {
        this.estimateHour = estimateHour;
    }

    public Long getInfactHour() {
        return infactHour;
    }

    public void setInfactHour(Long infactHour) {
        this.infactHour = infactHour;
    }

    public String getTaskMileage() {
        return taskMileage;
    }

    public void setTaskMileage(String taskMileage) {
        this.taskMileage = taskMileage == null ? null : taskMileage.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
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
}