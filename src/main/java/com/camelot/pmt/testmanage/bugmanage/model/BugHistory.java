package com.camelot.pmt.testmanage.bugmanage.model;

public class BugHistory {
    private Long id;

    private String num;

    private Long bugId;

    private String operationTime;

    private String operationId;

    private String operationFunction;

    private String operationRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Long getBugId() {
        return bugId;
    }

    public void setBugId(Long bugId) {
        this.bugId = bugId;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationFunction() {
        return operationFunction;
    }

    public void setOperationFunction(String operationFunction) {
        this.operationFunction = operationFunction;
    }

    public String getOperationRecord() {
        return operationRecord;
    }

    public void setOperationRecord(String operationRecord) {
        this.operationRecord = operationRecord;
    }

}