package com.camelot.pmt.platform.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义异常类
 *
 * @author Administrator
 * @date 2018-02-03 20:48
 */

public class SrfException extends RuntimeException {
    private static final long serialVersionUID = 1375330039151065929L;
    private JSONObject jsonObject;

    public SrfException(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public SrfException(String message) {
        super(message);
    }

    public SrfException(JSONObject jsonObject, Throwable cause) {
        super(cause);
        this.jsonObject = jsonObject;
    }

    public SrfException(String message, JSONObject jsonObject) {
        super(message);
        this.jsonObject = jsonObject;
    }

    public SrfException(String message, Throwable cause, JSONObject jsonObject) {
        super(message, cause);
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
