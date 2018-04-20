package com.camelot.pmt.common;

/**
 * 业务处理层异常
 * 
 * @author qiaodj
 * @date 2018年4月20日
 */
public class BussinessException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -2926927812327234780L;

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
