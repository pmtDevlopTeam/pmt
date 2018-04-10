package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class TaskLog implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 任务日志标识号
     */
    private Long taskLogId;
    /**
     * 任务标识号
     */
    private Long taskId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * 操作状态
     */
    private String operationType;
    /**
     * 操作描述
     */
    private String operationDescribe;

    public Long getTaskLogId() {
        return taskLogId;
    }

    public void setTaskLogId(Long taskLogId) {
        this.taskLogId = taskLogId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getOperationDescribe() {
        return operationDescribe;
    }

    public void setOperationDescribe(String operationDescribe) {
        this.operationDescribe = operationDescribe == null ? null : operationDescribe.trim();
    }
}