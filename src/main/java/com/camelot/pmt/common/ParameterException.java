package com.camelot.pmt.common;

/**
 * Controller层参数常
 * 
 * @author qiaodj
 * @date 2018年4月20日
 */
public class ParameterException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 4056722620061544427L;

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
