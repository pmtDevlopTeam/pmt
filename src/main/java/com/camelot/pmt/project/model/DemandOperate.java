package com.camelot.pmt.project.model;

import java.util.Date;

public class DemandOperate {
    /**
     * 主键
     */
    private Long id;

    /**
     * 需求id
     */
    private Long demandId;

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
     * 操作描述
     */
    private String runType;
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
     * 需求id
     * 
     * @return demand_id 需求id
     */
    public Long getDemandId() {
        return demandId;
    }

    /**
     * 需求id
     * 
     * @param demandId
     *            需求id
     */
    public void setDemandId(Long demandId) {
        this.demandId = demandId;
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

    /**
     * 操作描述
     * 
     * @return operate_desc 操作描述
     */
    public String getOperateDesc() {
        return operateDesc;
    }

    /**
     * 操作描述
     * 
     * @param operateDesc
     *            操作描述
     */
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc == null ? null : operateDesc.trim();
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }
}