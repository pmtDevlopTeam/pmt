package com.camelot.pmt.filemanage.model;

import java.util.Date;

/*
张战
 */
public class FileManage {
    private Long id;

    private Long groupId;

    private String fileTitle;

    private String fileAddress;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String delFlag;
    private int currentPage;
    private int pageSize;
    private int indexPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(int indexPage) {
        this.indexPage = indexPage;
    }

    @Override
    public String toString() {
        return "FileManage{" + "id=" + id + ", groupId=" + groupId + ", fileTitle='" + fileTitle + '\''
                + ", fileAddress='" + fileAddress + '\'' + ", createUserId=" + createUserId + ", createTime="
                + createTime + ", modifyUserId=" + modifyUserId + ", modifyTime=" + modifyTime + ", delFlag='" + delFlag
                + '\'' + ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", indexPage=" + indexPage + '}';
    }
}