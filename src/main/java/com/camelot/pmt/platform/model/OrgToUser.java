package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrgToUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 部门唯一32位id
     */
    private String orgId;
    /**
     * 部门名称
     */
    private String orgname;

    /**
     * 上级部门id
     */
    private String parentId;

    /**
     * 上级部门名称
     */
    private String orgParentName;
    /**
     * 部门编号
     */
    private String orgCode;
    /**
     * 状态
     */
    private String state;
    /**
     * 用户信息集合
     */
    private List<User> userList;
    /**
     * 用户对象信息
     */
    private User user;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgParentName() {
        return orgParentName;
    }

    public void setOrgParentName(String orgParentName) {
        this.orgParentName = orgParentName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建时间
     */
    private Date createTime;
}
