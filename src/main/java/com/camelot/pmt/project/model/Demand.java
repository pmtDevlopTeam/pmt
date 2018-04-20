package com.camelot.pmt.project.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class Demand {
    /**
     * 需求id
     */
    @XmlElement(name = "default")
    private Long id;

    /**
     * 所属一级需求id
     */
    @XmlElement(name = "default")
    private Long pid;

    /**
     * 项目id
     */
    @XmlElement(name = "default")
    private Long projectId;

    /**
     * 需求名称
     */
    @XmlElement(name = "需求名称")
    private String demandName;

    /**
     * 需求编号
     */
    @XmlElement(name = "需求编号")
    private String demandNum;

    /**
     * 需求状态:01未激活/02已激活/03已关闭/04已变更
     */
    @XmlElement(name = "需求状态")
    private String demandStatus;

    /**
     * 优先级
     */
    @XmlElement(name = "优先级")
    private String demandLevel;

    /**
     * 需求来源
     */
    @XmlElement(name = "需求来源")
    private String demandSource;

    /**
     * 需求来源备注
     */
    @XmlElement(name = "需求来源备注")
    private String sourceRemark;

    /**
     * 创建人id
     */
    @XmlElement(name = "default")
    private String createUserId;

    /**
     * 创建时间
     */
    @XmlElement(name = "default")
    private Date createTime;
    /**
     * 修改人id
     * 
     */
    @XmlElement(name = "default")
    private String modifyUserId;

    /**
     * 修改时间
     */
    @XmlElement(name = "default")
    private Date modifyTime;
    /**
     * 评审时间
     */
    @XmlElement(name = "default")
    private Date reviewTime;

    /**
     * 评审结果
     */
    @XmlElement(name = "评审结果")
    private String reviewResults;
    /**
     * 指派给
     */
    @XmlElement(name = "default")
    private String assignedTo;
    /**
     * 由谁评审
     */
    @XmlElement(name = "default")
    private String reviewedWith;
    /**
     * 评审备注
     */
    @XmlElement(name = "评审备注")
    private String reviewRemark;
    /**
     * 拒绝原因
     */
    @XmlElement(name = "拒绝原因")
    private String reasonsRejection;
    /**
     * 需求层级
     */
    @XmlElement(name = "default")
    private String demandNeed;
    /**
     * 需求描述
     */
    @XmlElement(name = "需求描述")
    private String demandDesc;

    /**
     * 关闭原因
     */
    @XmlElement(name = "关闭原因")
    private String closeReason;

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

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(String reviewResults) {
        this.reviewResults = reviewResults;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getReviewedWith() {
        return reviewedWith;
    }

    public void setReviewedWith(String reviewedWith) {
        this.reviewedWith = reviewedWith;
    }

    public String getReviewRemark() {
        return reviewRemark;
    }

    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }

    public String getReasonsRejection() {
        return reasonsRejection;
    }

    public void setReasonsRejection(String reasonsRejection) {
        this.reasonsRejection = reasonsRejection;
    }

    public String getDemandNeed() {
        return demandNeed;
    }

    public void setDemandNeed(String demandNeed) {
        this.demandNeed = demandNeed;
    }

    /**
     * 需求描述
     *
     * @return demand_desc 需求描述
     */
    public String getDemandDesc() {
        return demandDesc;
    }

    /**
     * 需求描述
     *
     * @param demandDesc
     *            需求描述
     */
    public void setDemandDesc(String demandDesc) {
        this.demandDesc = demandDesc == null ? null : demandDesc.trim();
    }

    /**
     * 关闭原因
     *
     * @return close_reason 关闭原因
     */
    public String getCloseReason() {
        return closeReason;
    }

    /**
     * 关闭原因
     *
     * @param closeReason
     *            关闭原因
     */
    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason == null ? null : closeReason.trim();
    }
}