package com.camelot.pmt.project.model;

import java.util.Date;

public class Demand {
    /**
     * 需求id
     */
    private Long id;

    /**
     * 所属一级需求id
     */
    private Long pid;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 需求名称
     */
    private String demandName;

    /**
     * 需求编号
     */
    private String demandNum;

    /**
     * 新建默认01：待评审,02：通过,03：不通过，04：冻结
     */
    private String demandStatus;

    /**
     * 优先级
     */
    private String demandLevel;

    /**
     * 需求来源
     */
    private String demandSource;

    /**
     * 需求来源备注
     */
    private String sourceRemark;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 需求id
     * 
     * @return id 需求id
     */
    public Long getId() {
        return id;
    }

    /**
     * 需求id
     * 
     * @param id
     *            需求id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属一级需求id
     * 
     * @return pid 所属一级需求id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 所属一级需求id
     * 
     * @param pid
     *            所属一级需求id
     */
    public void setPid(Long pid) {
        this.pid = pid;
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
     * 需求名称
     * 
     * @return demand_name 需求名称
     */
    public String getDemandName() {
        return demandName;
    }

    /**
     * 需求名称
     * 
     * @param demandName
     *            需求名称
     */
    public void setDemandName(String demandName) {
        this.demandName = demandName == null ? null : demandName.trim();
    }

    /**
     * 需求编号
     * 
     * @return demand_num 需求编号
     */
    public String getDemandNum() {
        return demandNum;
    }

    /**
     * 需求编号
     * 
     * @param demandNum
     *            需求编号
     */
    public void setDemandNum(String demandNum) {
        this.demandNum = demandNum == null ? null : demandNum.trim();
    }

    /**
     * 新建默认01：待评审,02：通过,03：不通过，04：冻结
     * 
     * @return demand_status 新建默认01：待评审,02：通过,03：不通过，04：冻结
     */
    public String getDemandStatus() {
        return demandStatus;
    }

    /**
     * 新建默认01：待评审,02：通过,03：不通过，04：冻结
     * 
     * @param demandStatus
     *            新建默认01：待评审,02：通过,03：不通过，04：冻结
     */
    public void setDemandStatus(String demandStatus) {
        this.demandStatus = demandStatus == null ? null : demandStatus.trim();
    }

    /**
     * 优先级
     * 
     * @return demand_level 优先级
     */
    public String getDemandLevel() {
        return demandLevel;
    }

    /**
     * 优先级
     * 
     * @param demandLevel
     *            优先级
     */
    public void setDemandLevel(String demandLevel) {
        this.demandLevel = demandLevel == null ? null : demandLevel.trim();
    }

    /**
     * 需求来源
     * 
     * @return demand_source 需求来源
     */
    public String getDemandSource() {
        return demandSource;
    }

    /**
     * 需求来源
     * 
     * @param demandSource
     *            需求来源
     */
    public void setDemandSource(String demandSource) {
        this.demandSource = demandSource == null ? null : demandSource.trim();
    }

    /**
     * 需求来源备注
     * 
     * @return source_remark 需求来源备注
     */
    public String getSourceRemark() {
        return sourceRemark;
    }

    /**
     * 需求来源备注
     * 
     * @param sourceRemark
     *            需求来源备注
     */
    public void setSourceRemark(String sourceRemark) {
        this.sourceRemark = sourceRemark == null ? null : sourceRemark.trim();
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
     * 修改人id
     * 
     * @return modify_user_id 修改人id
     */
    public String getModifyUserId() {
        return modifyUserId;
    }

    /**
     * 修改人id
     * 
     * @param modifyUserId
     *            修改人id
     */
    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    /**
     * 修改时间
     * 
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * 
     * @param modifyTime
     *            修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}