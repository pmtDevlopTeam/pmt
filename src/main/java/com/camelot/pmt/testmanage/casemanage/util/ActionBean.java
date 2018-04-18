package com.camelot.pmt.testmanage.casemanage.util;

/**
 * 
 * @author jh
 *
 */
public class ActionBean {

    private boolean result;

    private int code = 0;

    private String errorMessage;

    private Object response;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
