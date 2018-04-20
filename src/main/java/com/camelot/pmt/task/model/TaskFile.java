package com.camelot.pmt.task.model;

import java.io.Serializable;
import java.util.Date;

public class TaskFile implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String attachmentUrl;

    private String attachmentTile;

    private String attachmentSource;

    private Long sourceId;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl == null ? null : attachmentUrl.trim();
    }

    public String getAttachmentTile() {
        return attachmentTile;
    }

    public void setAttachmentTile(String attachmentTile) {
        this.attachmentTile = attachmentTile == null ? null : attachmentTile.trim();
    }

    public String getAttachmentSource() {
        return attachmentSource;
    }

    public void setAttachmentSource(String attachmentSource) {
        this.attachmentSource = attachmentSource == null ? null : attachmentSource.trim();
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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

    @Override
    public String toString() {
        return "TaskFile{" + "id=" + id + ", attachmentUrl='" + attachmentUrl + '\'' + ", attachmentTile='"
                + attachmentTile + '\'' + ", attachmentSource='" + attachmentSource + '\'' + ", sourceId=" + sourceId
                + ", createUserId=" + createUserId + ", createTime=" + createTime + ", modifyUserId=" + modifyUserId
                + ", modifyTime=" + modifyTime + '}';
    }
}