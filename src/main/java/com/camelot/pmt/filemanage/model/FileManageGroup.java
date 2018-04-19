package com.camelot.pmt.filemanage.model;

import java.util.Date;
import java.util.List;

/*
张战1
 */
public class FileManageGroup {
    private Long id;//文件夹id

    private Long parentId;//父级id

    private Long projectId;//项目id

    private String name;//文件夹名称
    private String fdescribe;//文件夹描述

    private String  createUserId;//创建人id

    private Date createTime;//创建时间

    private String modifyUserId;//修改人id

    private Date modifyTime;//修改时间

    private String delFlag;//删除状态（0未删除，1已删除）

    private Integer isfile;//判断是否是文件
    private int currentPage;//当前页
    private int pageSize;//每页显示条数
    private List<FileManageGroup> listGroup;//文件夹集合


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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
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

    public List<FileManageGroup> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<FileManageGroup> listGroup) {
        this.listGroup = listGroup;
    }

    @Override
    public String toString() {
        return "FileManageGroup{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", fdescribe='" + fdescribe + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createTime=" + createTime +
                ", modifyUserId='" + modifyUserId + '\'' +
                ", modifyTime=" + modifyTime +
                ", delFlag='" + delFlag + '\'' +
                ", isfile=" + isfile +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", listGroup=" + listGroup +
                '}';
    }
}