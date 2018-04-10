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

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

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
        return "TaskLog [taskLogId=" + taskLogId + ", taskId=" + taskId + ", userName=" + userName + ", operationTime="
                + operationTime + ", operationType=" + operationType + ", operationDescribe=" + operationDescribe
                + ", column1=" + column1 + ", column2=" + column2 + ", column3=" + column3 + ", column4=" + column4
                + ", column5=" + column5 + ", getTaskLogId()=" + getTaskLogId() + ", getTaskId()=" + getTaskId()
                + ", getUserName()=" + getUserName() + ", getOperationTime()=" + getOperationTime()
                + ", getOperationType()=" + getOperationType() + ", getOperationDescribe()=" + getOperationDescribe()
                + ", getColumn1()=" + getColumn1() + ", getColumn2()=" + getColumn2() + ", getColumn3()=" + getColumn3()
                + ", getColumn4()=" + getColumn4() + ", getColumn5()=" + getColumn5() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    public TaskLog(Long taskLogId, Long taskId, String userName, Date operationTime, String operationType,
            String operationDescribe, String column1, String column2, String column3, String column4, String column5) {
        super();
        this.taskLogId = taskLogId;
        this.taskId = taskId;
        this.userName = userName;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.operationDescribe = operationDescribe;
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
    }

    public TaskLog() {
        super();
    }

}