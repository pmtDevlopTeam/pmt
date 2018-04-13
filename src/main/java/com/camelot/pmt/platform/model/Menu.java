package com.camelot.pmt.platform.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 权限菜单表
 * </p>
 *
 * @author gnerv
 * @since 2018-04-11
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认索引 不可作用于业务
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 菜单唯一32位UUID 
     */
    private String menuId;
    /**
     * 上级菜单ID 默认 ‘0’  0级为顶级角色
     */
    private String parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型 1 目录 2 菜单 3 按钮
     */
    private String menuType;
    /**
     * 菜单请求地址 /xxx/xxx
     */
    private String menuUrl;
    /**
     * 菜单授权 模块:功能:方法  platform:menu:createMenu
     */
    private String menuPermission;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单状态  0（默认）启用 1 停用 2 锁定
     */
    private String state;
    /**
     * 排序号 默认 1000
     */
    private Integer sortNum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改人
     */
    private String modifyUserId;


    public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(Long id, String menuId, String parentId, String menuName, String menuType, String menuUrl,
			String menuPermission, String menuIcon, String state, Integer sortNum, Date createTime, String createUserId,
			Date modifyTime, String modifyUserId) {
		super();
		this.id = id;
		this.menuId = menuId;
		this.parentId = parentId;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuUrl = menuUrl;
		this.menuPermission = menuPermission;
		this.menuIcon = menuIcon;
		this.state = state;
		this.sortNum = sortNum;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(String menuPermission) {
        this.menuPermission = menuPermission;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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

    @Override
    public String toString() {
        return "PlatformMenu{" +
        ", id=" + id +
        ", menuId=" + menuId +
        ", parentId=" + parentId +
        ", menuName=" + menuName +
        ", menuType=" + menuType +
        ", menuUrl=" + menuUrl +
        ", menuPermission=" + menuPermission +
        ", menuIcon=" + menuIcon +
        ", state=" + state +
        ", sortNum=" + sortNum +
        ", createTime=" + createTime +
        ", createUserId=" + createUserId +
        ", modifyTime=" + modifyTime +
        ", modifyUserId=" + modifyUserId +
        "}";
    }
}
