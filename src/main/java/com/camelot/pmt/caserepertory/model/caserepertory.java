package com.camelot.pmt.caserepertory.model;

public class caserepertory {
    private Long id;

    private String caseTitle;

    private String caseType;

    private String description;

    private String applyPhase;

    private String num;

    private String precondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle == null ? null : caseTitle.trim();
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType == null ? null : caseType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getApplyPhase() {
        return applyPhase;
    }

    public void setApplyPhase(String applyPhase) {
        this.applyPhase = applyPhase == null ? null : applyPhase.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition == null ? null : precondition.trim();
    }
}