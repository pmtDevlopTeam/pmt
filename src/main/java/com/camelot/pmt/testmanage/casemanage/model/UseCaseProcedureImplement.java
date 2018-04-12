package com.camelot.pmt.testmanage.casemanage.model;

public class UseCaseProcedureImplement {
    private Long id;

    private Long implementId;

    private Long procedureId;

    private String testResult;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImplementId() {
        return implementId;
    }

    public void setImplementId(Long implementId) {
        this.implementId = implementId;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult == null ? null : testResult.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}