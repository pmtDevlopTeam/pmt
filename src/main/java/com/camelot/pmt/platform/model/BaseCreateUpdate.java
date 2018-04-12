package com.camelot.pmt.platform.model;

import java.util.Date;

public class BaseCreateUpdate {
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
    
	public BaseCreateUpdate() {
		super();
	}
	public BaseCreateUpdate(Date createTime, String createUserId, Date modifyTime, String modifyUserId) {
		super();
		this.createTime = createTime;
		this.createUserId = createUserId;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
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
