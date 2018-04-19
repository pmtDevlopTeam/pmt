package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class TaskLog implements Serializable{
	/**
	 * 任务日志标识号（主键）
	 */
    private Long id;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 操作人ID
     */
    private String userName;
    /**
     * 操作人名字
     */
    private String userId;
    /**
     * 操作时间
     */
    private Date operationTime;
    /**
     * 操作功能
     */
    private String operationButton;
    /**
     * 操作描述
     */
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}