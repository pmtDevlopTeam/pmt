package com.camelot.pmt.filemanage.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *
 * 文档分类表
 *
 */
@TableName("file_manage_group")
public class FileManageGroup implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 父级id */
    @TableField(value = "parent_id")
    private Long parentId;

    /** 项目ID */
    @TableField(value = "project_id")
    private Long projectId;

    /** 名称 */
    private String name;

    /** 创建者 */
    @TableField(value = "create_id")
    private Long createId;

    /** 创建时间 */
    @TableField(value = "create_time")
    private Date createTime;

    /** 更新者 */
    @TableField(value = "update_id")
    private Long updateId;

    /** 更新时间 */
    @TableField(value = "update_time")
    private Date updateTime;

    /** 删除标记 */
    @TableField(value = "del_flag")
    private String delFlag;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateId() {
        return this.createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
