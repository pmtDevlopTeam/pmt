package com.camelot.pmt.testmanage.bugmanage.model;

public class bugmanageKey {
    private Long id;

    private String bugTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle == null ? null : bugTitle.trim();
    }
}