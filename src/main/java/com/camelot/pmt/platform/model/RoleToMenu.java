package com.camelot.pmt.platform.model;

import java.util.Date;
import java.util.List;

/**
 * 角色 to 菜单
 */
public class RoleToMenu {
    /**
     * id
     */
    private Long id;
    /**
     * 角色唯一32位id
     */
    private String roleId;
    /**
     * 菜单唯一32位id
     */
    private List<String> menuId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    public RoleToMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RoleToMenu(Long id, String roleId, List<String> menuId, Date createTime, Date modifyTime) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
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

    public List<String> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<String> menuId) {
        this.menuId = menuId;
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

    @Override
    public String toString() {
        return "RoleToMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + ", createTime=" + createTime
                + ", modifyTime=" + modifyTime + "]";
    }

}
