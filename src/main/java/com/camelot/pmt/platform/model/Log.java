package com.camelot.pmt.platform.model;

import java.util.Date;

public class Log {


    private Long id;
    private String logType;
    private String logOperationType;
    private Date logOperationTime;
    private String logOperationUserId;
    private String logContent;
    private String logKeyWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogOperationType() {
        return logOperationType;
    }

    public void setLogOperationType(String logOperationType) {
        this.logOperationType = logOperationType;
    }

    public Date getLogOperationTime() {
        return logOperationTime;
    }

    public void setLogOperationTime(Date logOperationTime) {
        this.logOperationTime = logOperationTime;
    }

    public String getLogOperationUserId() {
        return logOperationUserId;
    }

    public void setLogOperationUserId(String logOperationUserId) {
        this.logOperationUserId = logOperationUserId;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getLogKeyWord() {
        return logKeyWord;
    }

    public void setLogKeyWord(String logKeyWord) {
        this.logKeyWord = logKeyWord;
    }
}
