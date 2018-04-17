package com.camelot.pmt.project.util.ilog;

import java.util.Date;

public class BaseLog {

	private String operator;// 操作人
	private Date operateTime;// 操作时间
	private String message;// 操作信息
	private Object beOperated;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBeOperated() {
		return beOperated;
	}

	public void setBeOperated(Object beOperated) {
		this.beOperated = beOperated;
	}

}
