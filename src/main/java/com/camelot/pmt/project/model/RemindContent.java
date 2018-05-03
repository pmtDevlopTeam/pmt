package com.camelot.pmt.project.model;

import java.util.Date;
import java.util.List;

public class RemindContent {
    /**
     * 主键
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 主表id
     */
    private Long remindId;

    /**
     * 内容编号
     */
    private String contentCode;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 提醒文本子内容
     */

    /**
     * 角色id
     */
    private String projectRoleId;

    public String getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(String projectRoleId) {
        this.projectRoleId = projectRoleId;
    }

    private List<RemindContentChild> remindContentChildList;

    public List<RemindContentChild> getRemindContentChildList() {
        return remindContentChildList;
    }

    public void setRemindContentChildList(List<RemindContentChild> remindContentChildList) {
        this.remindContentChildList = remindContentChildList;
    }

    /**
     * 主键
     * 
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * 
     * @param id
     *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 项目id
     * 
     * @return project_id 项目id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * 
     * @param projectId
     *            项目id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 主表id
     * 
     * @return remind_id 主表id
     */
    public Long getRemindId() {
        return remindId;
    }

    /**
     * 主表id
     * 
     * @param remindId
     *            主表id
     */
    public void setRemindId(Long remindId) {
        this.remindId = remindId;
    }

    /**
     * 内容编号
     * 
     * @return content_code 内容编号
     */
    public String getContentCode() {
        return contentCode;
    }

    /**
     * 内容编号
     * 
     * @param contentCode
     *            内容编号
     */
    public void setContentCode(String contentCode) {
        this.contentCode = contentCode == null ? null : contentCode.trim();
    }

    /**
     * 创建人id
     * 
     * @return create_user_id 创建人id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * 
     * @param createUserId
     *            创建人id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * 创建时间
     * 
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * 
     * @param createTime
     *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}