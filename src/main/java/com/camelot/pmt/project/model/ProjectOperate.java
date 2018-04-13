package com.camelot.pmt.project.model;

import java.util.Date;

public class ProjectOperate {
    /**
     * id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * id
     * @return id id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 项目id
     * @return project_id 项目id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * 创建人id
     * @return create_user_id 创建人id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * @param createUserId 创建人id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 操作描述
     * @return operate_desc 操作描述
     */
    public String getOperateDesc() {
        return operateDesc;
    }

    /**
     * 操作描述
     * @param operateDesc 操作描述
     */
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc == null ? null : operateDesc.trim();
    }
}