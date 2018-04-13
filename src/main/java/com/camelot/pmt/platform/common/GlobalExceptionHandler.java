package com.camelot.pmt.platform.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author Administrator
 * @date 2018-02-03 12:30
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>
     * 运行时异常处理
     * </p>
     * 
     * @param e
     *            UnknownAccountException
     * @return {"status":{"code":404,"message":"用户不存在."}}
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String error(Exception e) {
        return e.getMessage();
    }

    /**
     * <p>
     * 异常处理
     * </p>
     * 
     * @param e
     *            UnknownAccountException
     * @return {"status":{"code":404,"message":"."}}
     */
    @ExceptionHandler(SrfException.class)
    @ResponseBody
    public String srferror(SrfException e) {
        return e.getJsonObject().toJSONString();
    }

    /**
     * <p>
     * 用户不存在异常处理
     * </p>
     * 
     * @param e
     *            UnknownAccountException
     * @return {"status":{"code":404,"message":"用户不存在."}}
     */
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public JSONObject unknownAccountException(Exception e) {
        return ApiResponse.jsonData(APIStatus.NOT_USERNAME_404);
    }

    /**
     * <p>
     * 未授权
     * </p>
     * 
     * @param e
     *            UnknownAccountException
     * @return {"status":{"code":404,"message":"未授权."}}
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public JSONObject unauthorizedException(Exception e) {
        return ApiResponse.jsonData(APIStatus.FORBIDDEN_403);
    }

}
