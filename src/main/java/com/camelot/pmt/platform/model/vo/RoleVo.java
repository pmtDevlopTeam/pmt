package com.camelot.pmt.platform.model.vo;

import java.util.Date;

public class RoleVo {

    /**
     * id
     */
    private Long id;
    /**
     * 角色唯一32位id
     */
    private String roleId;
    /**
     * 顶级角色 0为顶级角色
     */
    private String parentId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色状态
     */
    private String state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String createUserNmae;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUserNmae() {
        return createUserNmae;
    }

    public void setCreateUserNmae(String createUserNmae) {
        this.createUserNmae = createUserNmae;
    }
}
