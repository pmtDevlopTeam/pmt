package com.camelot.pmt.platform.model;

import java.util.Date;

/**
 * 角色绑定权限实体
 */
public class RoleToMenu {

    /**
     * id
     */
    private Long id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 权限菜单ID
     */
    private String menuId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 菜单id集合
     */
    private String[] menuIds;

    public String[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String[] menuIds) {
        this.menuIds = menuIds;
    }

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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
