package com.camelot.pmt.task.model;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.ProjectMain;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
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
     * 项目
     */
    private ProjectMain project;
    /**
     * 需求
     */
    private Demand demand;
    /**
     * 优先级
     */
    private String priority;
    /**
     * 指派人ID
     */
    private User assignUser;
    /**
     * 负责人ID
     */
    private User beassignUser;
    /**
     * 任务指派时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date assignTime;
    /**
     * 任务预计开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date estimateStartTime;
    /**
     * 任务预计结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date estimateEndTime;
    /**
     * 任务实际开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actualStartTime;
    /**
     * 任务实际结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    private User createUser;
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
    /**
     * 已经消耗工时
     */
    private String elapsedTime;

    /**
     * 剩余工时
     */
    private String remainingWork;
    /**
     * 任务对应历史记录
     */
    private List<TaskLog> taskLogList;

    public Task() {
    }

    public Task(Long id, String taskNum, String taskName, Long taskParentId, ProjectMain project, Demand demand,
            String priority, User assignUser, User beassignUser, Date assignTime, Date estimateStartTime,
            Date estimateEndTime, Date actualStartTime, Date actualEndTime, String taskType, String taskSpeed,
            String status, String nodeLv, String taskDescribe, String demandChange, String delayDescribe,
            Long estimateHour, Long infactHour, String taskMileage, User createUser, String modifyUserId,
            Date createTime, Date modifyTime, List<Task> children) {
        this.id = id;
        this.taskNum = taskNum;
        this.taskName = taskName;
        this.taskParentId = taskParentId;
        this.project = project;
        this.demand = demand;
        this.priority = priority;
        this.assignUser = assignUser;
        this.beassignUser = beassignUser;
        this.assignTime = assignTime;
        this.estimateStartTime = estimateStartTime;
        this.estimateEndTime = estimateEndTime;
        this.actualStartTime = actualStartTime;
        this.actualEndTime = actualEndTime;
        this.taskType = taskType;
        this.taskSpeed = taskSpeed;
        this.status = status;
        this.nodeLv = nodeLv;
        this.taskDescribe = taskDescribe;
        this.demandChange = demandChange;
        this.delayDescribe = delayDescribe;
        this.estimateHour = estimateHour;
        this.infactHour = infactHour;
        this.taskMileage = taskMileage;
        this.createUser = createUser;
        this.modifyUserId = modifyUserId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
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
        this.taskNum = taskNum;
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

    public ProjectMain getProject() {
        return project;
    }

    public void setProject(ProjectMain project) {
        this.project = project;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(User assignUser) {
        this.assignUser = assignUser;
    }

    public User getBeassignUser() {
        return beassignUser;
    }

    public void setBeassignUser(User beassignUser) {
        this.beassignUser = beassignUser;
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

    public String getNodeLv() {
        return nodeLv;
    }

    public void setNodeLv(String nodeLv) {
        this.nodeLv = nodeLv;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getDemandChange() {
        return demandChange;
    }

    public void setDemandChange(String demandChange) {
        this.demandChange = demandChange;
    }

    public String getDelayDescribe() {
        return delayDescribe;
    }

    public void setDelayDescribe(String delayDescribe) {
        this.delayDescribe = delayDescribe;
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
        this.taskMileage = taskMileage;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
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

    public List<Task> getChildren() {
        return children;
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getRemainingWork() {
        return remainingWork;
    }

    public void setRemainingWork(String remainingWork) {
        this.remainingWork = remainingWork;
    }

    public List<TaskLog> getTaskLogList() {
        return taskLogList;
    }

    public void setTaskLogList(List<TaskLog> taskLogList) {
        this.taskLogList = taskLogList;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", taskNum=" + taskNum + ", taskName=" + taskName + ", taskParentId=" + taskParentId
                + ", project=" + project + ", demand=" + demand + ", priority=" + priority + ", assignUser="
                + assignUser + ", beassignUser=" + beassignUser + ", assignTime=" + assignTime + ", estimateStartTime="
                + estimateStartTime + ", estimateEndTime=" + estimateEndTime + ", actualStartTime=" + actualStartTime
                + ", actualEndTime=" + actualEndTime + ", taskType=" + taskType + ", taskSpeed=" + taskSpeed
                + ", status=" + status + ", nodeLv=" + nodeLv + ", taskDescribe=" + taskDescribe + ", demandChange="
                + demandChange + ", delayDescribe=" + delayDescribe + ", estimateHour=" + estimateHour + ", infactHour="
                + infactHour + ", taskMileage=" + taskMileage + ", createUser=" + createUser + ", modifyUserId="
                + modifyUserId + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", children=" + children
                + ", elapsedTime=" + elapsedTime + ", remainingWork=" + remainingWork + "]";
    }

}