package com.camelot.pmt.task.model;

import java.util.Date;

public class TaskLog {
    private Long id;

    private Long taskId;

    private String userId;

    private Date operationTime;

    private String operationButton;

    private String operationDescribe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationButton() {
        return operationButton;
    }

    public void setOperationButton(String operationButton) {
        this.operationButton = operationButton == null ? null : operationButton.trim();
    }

    public String getOperationDescribe() {
        return operationDescribe;
    }

    public void setOperationDescribe(String operationDescribe) {
        this.operationDescribe = operationDescribe == null ? null : operationDescribe.trim();
    }
}