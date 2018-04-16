package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class TaskLog implements Serializable{

    private static final long serialVersionUID = 3340107132316964404L;

    private Long id;

    private Long taskId;

    private String userName;

    private Date operationTime;

    private String operationType;

    private String operationDescribe;

    private String createUserId;

    private String modifyUserId;

    private Date createTime;

    private Date modifyTime;

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

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

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2 == null ? null : column2.trim();
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3 == null ? null : column3.trim();
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4 == null ? null : column4.trim();
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5 == null ? null : column5.trim();
    }

	@Override
	public String toString() {
		return "TaskLog [id=" + id + ", taskId=" + taskId + ", userName=" + userName + ", operationTime="
				+ operationTime + ", operationType=" + operationType + ", operationDescribe=" + operationDescribe
				+ ", createUserId=" + createUserId + ", modifyUserId=" + modifyUserId + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", column1=" + column1 + ", column2=" + column2 + ", column3="
				+ column3 + ", column4=" + column4 + ", column5=" + column5 + "]";
	}
	

	public TaskLog() {
		super();
	}

	public TaskLog(Long id, Long taskId, String userName, Date operationTime, String operationType,
			String operationDescribe, String createUserId, String modifyUserId, Date createTime, Date modifyTime,
			String column1, String column2, String column3, String column4, String column5) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.userName = userName;
		this.operationTime = operationTime;
		this.operationType = operationType;
		this.operationDescribe = operationDescribe;
		this.createUserId = createUserId;
		this.modifyUserId = modifyUserId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
		this.column4 = column4;
		this.column5 = column5;
	}
    
}