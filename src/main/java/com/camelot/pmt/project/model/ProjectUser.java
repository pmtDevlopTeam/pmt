package com.camelot.pmt.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectUser {

    /** 进入 */
    public static final String STATUS_IN = "01";
    /** 未进入 */
    public static final String STATUS_NOT_IN = "02";
    /** 已出 */
    public static final String STATUS_OUT = "03";
    /** 暂离 */
    public static final String STATUS_AFK = "04";

    /**
     * id
     */
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 预计进项目日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date preJoinTime;

    /**
     * 实际进项目日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date realJoinTime;

    /**
     * 预计出项目日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date preOutTime;

    /**
     * 实际出项目日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date realOutTime;

    /**
     * 预计工时
     */
    private Integer preManHour;

    /**
     * 成员在项目状态 01进入，02未进入，03已出 04 暂离
     */
    private String userStatus;

    /**
     * 成员在项目角色id
     */
    private String userProRole;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date createTime;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date modifyTime;

    /**
     * id
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * 
     * @param id
     *            id
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
     * 用户id
     * 
     * @return user_id 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * 
     * @param userId
     *            用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 预计进项目日期
     * 
     * @return pre_join_time 预计进项目日期
     */
    public Date getPreJoinTime() {
        return preJoinTime;
    }

    /**
     * 预计进项目日期
     * 
     * @param preJoinTime
     *            预计进项目日期
     */
    public void setPreJoinTime(Date preJoinTime) {
        this.preJoinTime = preJoinTime;
    }

    /**
     * 实际进项目日期
     * 
     * @return real_join_time 实际进项目日期
     */
    public Date getRealJoinTime() {
        return realJoinTime;
    }

    /**
     * 实际进项目日期
     * 
     * @param realJoinTime
     *            实际进项目日期
     */
    public void setRealJoinTime(Date realJoinTime) {
        this.realJoinTime = realJoinTime;
    }

    /**
     * 预计出项目日期
     * 
     * @return pre_out_time 预计出项目日期
     */
    public Date getPreOutTime() {
        return preOutTime;
    }

    /**
     * 预计出项目日期
     * 
     * @param preOutTime
     *            预计出项目日期
     */
    public void setPreOutTime(Date preOutTime) {
        this.preOutTime = preOutTime;
    }

    /**
     * 实际出项目日期
     * 
     * @return real_out_time 实际出项目日期
     */
    public Date getRealOutTime() {
        return realOutTime;
    }

    /**
     * 实际出项目日期
     * 
     * @param realOutTime
     *            实际出项目日期
     */
    public void setRealOutTime(Date realOutTime) {
        this.realOutTime = realOutTime;
    }

    /**
     * 预计工时
     * 
     * @return pre_man_hour 预计工时
     */
    public Integer getPreManHour() {
        return preManHour;
    }

    /**
     * 预计工时
     * 
     * @param preManHour
     *            预计工时
     */
    public void setPreManHour(Integer preManHour) {
        this.preManHour = preManHour;
    }

    /**
     * 成员在项目状态 01进入，02未进入，03已出 04 暂离
     * 
     * @return user_status 成员在项目状态 01进入，02未进入，03已出 04 暂离
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 成员在项目状态 01进入，02未进入，03已出 04 暂离
     * 
     * @param userStatus
     *            成员在项目状态 01进入，02未进入，03已出 04 暂离
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 成员在项目角色id
     * 
     * @return user_pro_role 成员在项目角色id
     */
    public String getUserProRole() {
        return userProRole;
    }

    /**
     * 成员在项目角色id
     * 
     * @param userProRole
     *            成员在项目角色id
     */
    public void setUserProRole(String userProRole) {
        this.userProRole = userProRole;
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