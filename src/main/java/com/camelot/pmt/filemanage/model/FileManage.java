package com.camelot.pmt.filemanage.model;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *
 * 文档表
 *
 */
@TableName("file_manage")
public class FileManage implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 文件类型id */
	@TableField(value = "group_id")
	private Long groupId;

	/** 文件标题 */
	@TableField(value = "file_title")
	private String fileTitle;

	/** svn/git等地址 */
	@TableField(value = "file_address")
	private String fileAddress;

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

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getFileTitle() {
		return this.fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileAddress() {
		return this.fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
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
