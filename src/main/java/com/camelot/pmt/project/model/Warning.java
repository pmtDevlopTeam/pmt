package com.camelot.pmt.project.model;

import java.util.Date;

public class Warning {
    /**
     * 预警id
     */
    private Long id;

    /**
     * 预警类型
     */
    private String warnType;

    /**
     * 项目id
     */
    private Long proId;

    /**
     * 自定义预警时间（项目和任务用到）
     */
    private Date warnTime;

    /**
     * bug最小条数预警
     */
    private Integer minNum;

    /**
     * bug最大条数预警
     */
    private Integer maxNum;

    /**
     * 预警状态
     */
    private String warnStatus;

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
     * 任务id
     */
    private Long taskId;

    /**
     * 预警id
     * @return id 预警id
     */
    public Long getId() {
        return id;
    }

    /**
     * 预警id
     * @param id 预警id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 预警类型
     * @return warn_type 预警类型
     */
    public String getWarnType() {
        return warnType;
    }

    /**
     * 预警类型
     * @param warnType 预警类型
     */
    public void setWarnType(String warnType) {
        this.warnType = warnType == null ? null : warnType.trim();
    }

    /**
     * 项目id
     * @return pro_id 项目id
     */
    public Long getProId() {
        return proId;
    }

    /**
     * 项目id
     * @param proId 项目id
     */
    public void setProId(Long proId) {
        this.proId = proId;
    }

    /**
     * 自定义预警时间（项目和任务用到）
     * @return warn_time 自定义预警时间（项目和任务用到）
     */
    public Date getWarnTime() {
        return warnTime;
    }

    /**
     * 自定义预警时间（项目和任务用到）
     * @param warnTime 自定义预警时间（项目和任务用到）
     */
    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    /**
     * bug最小条数预警
     * @return min_num bug最小条数预警
     */
    public Integer getMinNum() {
        return minNum;
    }

    /**
     * bug最小条数预警
     * @param minNum bug最小条数预警
     */
    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    /**
     * bug最大条数预警
     * @return max_num bug最大条数预警
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * bug最大条数预警
     * @param maxNum bug最大条数预警
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * 预警状态
     * @return warn_status 预警状态
     */
    public String getWarnStatus() {
        return warnStatus;
    }

    /**
     * 预警状态
     * @param warnStatus 预警状态
     */
    public void setWarnStatus(String warnStatus) {
        this.warnStatus = warnStatus == null ? null : warnStatus.trim();
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

    /**
     * 任务id
     * @return task_id 任务id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 任务id
     * @param taskId 任务id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}