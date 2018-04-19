package com.camelot.pmt.filemanage.model;

import java.util.Date;
/*
张战1
 */
public class FileManageGroup {
    private Long id;

    private Long parentId;

    private Long projectId;

    private String name;
    private String fdescribe;

    private Long  createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String delFlag;

    private Integer isfile;
    private int currentPage;
    private int pageSize;
    private int indexPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFdescribe() {
        return fdescribe;
    }

    public void setFdescribe(String fdescribe) {
        this.fdescribe = fdescribe;
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

    public Integer getIsfile() {
        return isfile;
    }

    public void setIsfile(Integer isfile) {
        this.isfile = isfile;
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
        return "FileManageGroup{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", fdescribe='" + fdescribe + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", modifyUserId=" + modifyUserId +
                ", modifyTime=" + modifyTime +
                ", delFlag='" + delFlag + '\'' +
                ", isfile=" + isfile +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", indexPage=" + indexPage +
                '}';
    }
}