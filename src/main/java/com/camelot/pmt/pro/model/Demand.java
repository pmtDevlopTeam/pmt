package com.camelot.pmt.pro.model;

import java.util.Date;

public class Demand {
    private Integer reqId;

    private Integer proId;

    private Integer firstLevelId;

    private String reqName;

    private String reqStatus;

    private String reqLevel;

    private String reqFile;

    private String reqCode;

    private String fileTitle;

    private String reqSource;

    private String sourceNotes;

    private Integer userId;

    private Date createTime;

    private Integer modifyUserId;

    private Date modifyTime;

    private String column1;

    private String column2;

    private String column3;

    private String column4;

    private String column5;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getFirstLevelId() {
        return firstLevelId;
    }

    public void setFirstLevelId(Integer firstLevelId) {
        this.firstLevelId = firstLevelId;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName == null ? null : reqName.trim();
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus == null ? null : reqStatus.trim();
    }

    public String getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(String reqLevel) {
        this.reqLevel = reqLevel == null ? null : reqLevel.trim();
    }

    public String getReqFile() {
        return reqFile;
    }

    public void setReqFile(String reqFile) {
        this.reqFile = reqFile == null ? null : reqFile.trim();
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode == null ? null : reqCode.trim();
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle == null ? null : fileTitle.trim();
    }

    public String getReqSource() {
        return reqSource;
    }

    public void setReqSource(String reqSource) {
        this.reqSource = reqSource == null ? null : reqSource.trim();
    }

    public String getSourceNotes() {
        return sourceNotes;
    }

    public void setSourceNotes(String sourceNotes) {
        this.sourceNotes = sourceNotes == null ? null : sourceNotes.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
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
}