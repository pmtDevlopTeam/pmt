package com.camelot.pmt.task.model;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.project.model.Demand;
import com.camelot.pmt.project.model.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private Long id;

    private String taskNum;

    private String taskName;

    private Long taskParentId;

    private Project project;

    private Demand demand;

    private String priority;

    private User assignUser;

    private User beassignUser;

    private Date assignTime;

    private Date estimateStartTime;

    private Date estimateEndTime;

    private Date actualStartTime;

    private Date actualEndTime;

    private String taskType;

    private String taskSpeed;

    private String status;

    private String nodeLv;

    private String taskDescribe;

    private String demandChange;

    private String delayDescribe;

    private Long estimateHour;

    private Long infactHour;

    private String taskMileage;

    private User createUser;

    private String modifyUserId;

    private Date createTime;

    private Date modifyTime;

    private List<Task> children = new ArrayList<Task>();

    public Task() {
    }

    public Task(Long id, String taskNum, String taskName, Long taskParentId, Project project, Demand demand, String priority, User assignUser, User beassignUser, Date assignTime, Date estimateStartTime, Date estimateEndTime, Date actualStartTime, Date actualEndTime, String taskType, String taskSpeed, String status, String nodeLv, String taskDescribe, String demandChange, String delayDescribe, Long estimateHour, Long infactHour, String taskMileage, User createUser, String modifyUserId, Date createTime, Date modifyTime, List<Task> children) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskNum='" + taskNum + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskParentId=" + taskParentId +
                ", project=" + project +
                ", demand=" + demand +
                ", priority='" + priority + '\'' +
                ", assignUser=" + assignUser +
                ", beassignUser=" + beassignUser +
                ", assignTime=" + assignTime +
                ", estimateStartTime=" + estimateStartTime +
                ", estimateEndTime=" + estimateEndTime +
                ", actualStartTime=" + actualStartTime +
                ", actualEndTime=" + actualEndTime +
                ", taskType='" + taskType + '\'' +
                ", taskSpeed='" + taskSpeed + '\'' +
                ", status='" + status + '\'' +
                ", nodeLv='" + nodeLv + '\'' +
                ", taskDescribe='" + taskDescribe + '\'' +
                ", demandChange='" + demandChange + '\'' +
                ", delayDescribe='" + delayDescribe + '\'' +
                ", estimateHour=" + estimateHour +
                ", infactHour=" + infactHour +
                ", taskMileage='" + taskMileage + '\'' +
                ", createUser=" + createUser +
                ", modifyUserId='" + modifyUserId + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", children=" + children +
                '}';
    }
}