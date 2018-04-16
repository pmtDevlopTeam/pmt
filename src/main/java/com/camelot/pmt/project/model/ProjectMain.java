package com.camelot.pmt.project.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjectMain implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 负责人id
     */
    private String userId;

    /**
     * 编号
     */
    private String projectNum;

    /**
     * 名称
     */
    private String projectName;

    /**
     * 状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭
     */
    private String projectStatus;

    /**
     * 起始时间（是立项时预计）
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间（是立项时预计）
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 描述
     */
    private String projectDesc;

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
     * 负责人id
     * 
     * @return user_id 负责人id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 负责人id
     * 
     * @param userId
     *            负责人id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 编号
     * 
     * @return project_num 编号
     */
    public String getProjectNum() {
        return projectNum;
    }

    /**
     * 编号
     * 
     * @param projectNum
     *            编号
     */
    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum == null ? null : projectNum.trim();
    }

    /**
     * 名称
     * 
     * @return project_name 名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 名称
     * 
     * @param projectName
     *            名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    /**
     * 状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭
     * 
     * @return project_status 状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * 状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭
     * 
     * @param projectStatus
     *            状态 01未开始 02进行中 03完成 04延期 05挂起 06关闭
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    /**
     * 起始时间（是立项时预计）
     * 
     * @return start_time 起始时间（是立项时预计）
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 起始时间（是立项时预计）
     * 
     * @param startTime
     *            起始时间（是立项时预计）
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间（是立项时预计）
     * 
     * @return end_time 结束时间（是立项时预计）
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间（是立项时预计）
     * 
     * @param endTime
     *            结束时间（是立项时预计）
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    /**
     * 描述
     * 
     * @return project_desc 描述
     */
    public String getProjectDesc() {
        return projectDesc;
    }

    /**
     * 描述
     * 
     * @param projectDesc
     *            描述
     */
    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }
}