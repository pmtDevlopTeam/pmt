package com.camelot.pmt.platform.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Title:  ExecuteResult.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: jh
 */
public class ExecuteResult <T> implements Serializable {

	private static final long serialVersionUID = -1854616725284151074L;

	private T result;
	private String resultMessage;
	private List<String> errorMessages = new ArrayList<String>();
	
	public boolean isSuccess() {
		return errorMessages.isEmpty()?true:false;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void addErrorMessage(String errorMessage) {
		errorMessages.add(errorMessage);
	}
	

	public String getErrorMessage() {
		return errorMessages.get(0);
	}
}
