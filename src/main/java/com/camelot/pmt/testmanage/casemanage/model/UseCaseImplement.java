package com.camelot.pmt.testmanage.casemanage.model;

import java.util.Date;

public class UseCaseImplement {
    private Long id;

    private Long useCaseId;

    private String implementResult;

    private Date executeTime;

    private Long executeUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUseCaseId() {
        return useCaseId;
    }

    public void setUseCaseId(Long useCaseId) {
        this.useCaseId = useCaseId;
    }

    public String getImplementResult() {
        return implementResult;
    }

    public void setImplementResult(String implementResult) {
        this.implementResult = implementResult == null ? null : implementResult.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Long getExecuteUserId() {
        return executeUserId;
    }

    public void setExecuteUserId(Long executeUserId) {
        this.executeUserId = executeUserId;
    }
}