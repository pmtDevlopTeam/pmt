package com.camelot.pmt.filemanage.model;

import java.util.Date;
/*
张战
 */
public class FileManage {
    private Long id;//文件id

    private Long groupId;//组id

    private String fileTitle;//文件标题

    private String fileAddress;//文件路径

    private Long  createUserId;//创建人id

    private Date createTime;//创建时间

    private Long   modifyUserId;//修改人id

    private Date modifyTime;//修改时间

    private String delFlag;//删除状态（0未删除，1删除）
    private int currentPage;//当前页
    private int pageSize;//每页显示数量

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

    @Override
    public String toString() {
        return "FileManage{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", fileTitle='" + fileTitle + '\'' +
                ", fileAddress='" + fileAddress + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", modifyUserId=" + modifyUserId +
                ", modifyTime=" + modifyTime +
                ", delFlag='" + delFlag + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}