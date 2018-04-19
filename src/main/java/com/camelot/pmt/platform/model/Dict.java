package com.camelot.pmt.platform.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class Dict implements Serializable {
	/**
	 * @Description:字典表
	 *
	 * @author pmt
	 * @since 2018-04-11
	 */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * 默认索引 不可作用于业务 
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
    /**
     * 字典唯一32位UUID 
     */
    private String dictId;
    /**
     * 字典编码 唯一
     */
	private String dictCode;
    /**
     * 字典类型
     */
	private String dictType;
    /**
     * 字典名称 唯一
     */
    private String dictName;
	/**
	 * 状态（默认）启用 1 停用 2 锁定
	 */
    private String state;
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
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
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
	
	public Dict() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dict(Long id, String dictId, String dictCode, String dictType, String dictName, String state,
			Date createTime, String createUserId, Date modifyTime, String modifyUserId) {
		super();
		this.id = id;
		this.dictId = dictId;
		this.dictCode = dictCode;
		this.dictType = dictType;
		this.dictName = dictName;
		this.state = state;
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
	}
	@Override
	public String toString() {
		return "Dict [id=" + id + ", dictId=" + dictId + ", dictCode=" + dictCode + ", dictType=" + dictType
				+ ", dictName=" + dictName + ", state=" + state + ", createTime=" + createTime + ", createUserId="
				+ createUserId + ", modifyTime=" + modifyTime + ", modifyUserId=" + modifyUserId + "]";
	}



    
    
}