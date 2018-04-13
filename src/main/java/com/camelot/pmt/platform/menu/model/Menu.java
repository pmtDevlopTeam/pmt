package com.camelot.pmt.platform.menu.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gnerv
 * @since 2018-04-08
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认索引 不可作用于业务
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户唯一32位UUID
     */
    @TableField("menuId")
    private String menuId;
    /**
     * 默认 ‘0’ 0为顶级角色
     */
    @TableField("parentId")
    private String parentId;
    @TableField("menuName")
    private String menuName;
    /**
     * 1目录 2菜单 3按钮
     */
    @TableField("menuType")
    private String menuType;
    @TableField("menuURL")
    private String menuURL;
    @TableField("menuPermission")
    private String menuPermission;
    @TableField("menuIcon")
    private String menuIcon;
    /**
     * 0（默认）启用 1 停用 2 锁定
     */
    private String state;
    /**
     * 默认 1000
     */
    @TableField("sortNum")
    private Integer sortNum;
    private Date createtime;
    private Date modifytime;

    private List<Menu> children = new ArrayList<Menu>();

    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Menu(Integer id, String menuId, String parentId, String menuName, String menuType, String menuURL,
            String menuPermission, String menuIcon, String state, Integer sortNum, Date createtime, Date modifytime,
            List<Menu> children) {
        super();
        this.id = id;
        this.menuId = menuId;
        this.parentId = parentId;
        this.menuName = menuName;
        this.menuType = menuType;
        this.menuURL = menuURL;
        this.menuPermission = menuPermission;
        this.menuIcon = menuIcon;
        this.state = state;
        this.sortNum = sortNum;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", menuId=" + menuId + ", parentId=" + parentId + ", menuName=" + menuName
                + ", menuType=" + menuType + ", menuURL=" + menuURL + ", menuPermission=" + menuPermission
                + ", menuIcon=" + menuIcon + ", state=" + state + ", sortNum=" + sortNum + ", createtime=" + createtime
                + ", modifytime=" + modifytime + ", children=" + children + "]";
    }

}
