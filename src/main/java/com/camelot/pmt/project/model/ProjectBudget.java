package com.camelot.pmt.project.model;

import java.util.Date;

public class ProjectBudget {
    /**
     * 预算id
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 实际工时
     */
    private Integer actualHours;

    /**
     * 预计工时
     */
    private Integer budgetaryHours;

    /**
     * 其它
     */
    private String other;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人id
     */
    private Long modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 预算id
     * @return id 预算id
     */
    public Long getId() {
        return id;
    }

    /**
     * 预算id
     * @param id 预算id
     */
    public void setId(Long id) {
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
     * 实际工时
     * @return actual_hours 实际工时
     */
    public Integer getActualHours() {
        return actualHours;
    }

    /**
     * 实际工时
     * @param actualHours 实际工时
     */
    public void setActualHours(Integer actualHours) {
        this.actualHours = actualHours;
    }

    /**
     * 预计工时
     * @return budgetary_hours 预计工时
     */
    public Integer getBudgetaryHours() {
        return budgetaryHours;
    }

    /**
     * 预计工时
     * @param budgetaryHours 预计工时
     */
    public void setBudgetaryHours(Integer budgetaryHours) {
        this.budgetaryHours = budgetaryHours;
    }

    /**
     * 其它
     * @return other 其它
     */
    public String getOther() {
        return other;
    }

    /**
     * 其它
     * @param other 其它
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 创建人id
     * @return create_user_id 创建人id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * @param createUserId 创建人id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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
     * 修改人id
     * @return modify_user_id 修改人id
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 修改人id
     * @param modifyUserId 修改人id
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}