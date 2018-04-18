package com.camelot.pmt.common;

/**
 * 
 * <p>
 * Description: [API 请求结果-状态码]
 * </p>
 *
 * @version 1.0 Copyright (c) 北京柯莱特科技有限公司 交付部
 */
public enum APIStatus {
    /** 404 Not Found. 访问的API不存在. */
    NOT_FOUND_404(404, "访问的API不存在.", "Not Found."),
    /** 404 Not Username 用户不存在. */
    NOT_USERNAME_404(404, "用户不存在.", "Not Username."),
    /** 403 Not Found. 禁止访问,没有权限. */
    FORBIDDEN_403(403, "禁止访问,没有权限.", "No permission to access."),
    /** 401 Unauthorized. 未登录或登录状态已失效. */
    UNAUTHORIZED_401(401, "未登录或登录状态已失效.", "No login or login status has expired."),
    /** 200 Ok. 请求处理成功. */
    OK_200(200, "请求处理成功.", "OK"),
    /** 205 角色名称可用 */
    OK_205(205, "角色名称可用", "Role names are available"),
    /** 206 角色名称不可用 */
    OK_206(206, "角色名称不可用", "Role name is not available"),
    /** 500 Internal Server Error. 服务器内部错误,请求处理失败. */
    ERROR_500(500, "服务器内部错误,请求处理失败.", "Internal Server Error."),
    /** 501 Unknown Cause Delete Failed. 未知原因,删除失败. */
    ERROR_501(501, "未知原因,删除失败.", "Unknown Cause Delete Failed."),
    /** 502 删除用户有任务占用，无法删除 */
    ERROR_502(502, "用户有任务占用，无法删除", "删除用户有任务占用，无法删除"),
    /** 503 删除需求有引用占用，无法删除 */
    ERROR_503(503, "需求有引用务占用，无法删除", "删除需求有引用占用，无法删除"),
    /** 400 Bad Request. 请求参数有问题. */
    ERROR_400(400, "请求参数有问题.", "Bad Request."),
    /** 1 logout. 退出成功. */
    LOGOUT(1, "退出成功.", "logout."),
    /** 1 logout. 退出成功. */
    INVALIDSESSION_LOGINOUTTIME(2, "登录超时.", "loginouttime.");

    private int code;
    private String message;
    private String englishMessage;

    APIStatus(int code, String message, String englishMessage) {
        this.code = code;
        this.message = message;
        this.englishMessage = englishMessage;
    }

    public String getEnglishMessage() {
        return englishMessage;
    }

    public void setEnglishMessage(String englishMessage) {
        this.englishMessage = englishMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
