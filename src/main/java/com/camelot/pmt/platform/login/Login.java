package com.camelot.pmt.platform.login;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.common.ApiResponse;
import com.camelot.pmt.platform.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 
 * @author gnerv
 * @Description 用户登录、退出
 * @date 2018年4月18日
 */
@ResponseBody
@RestController
@Api(value = "用户登陆接口", description = "用户登陆接口")
public class Login {

    /**
     * 登陆
     */
    @ApiOperation(value = "登陆接口", notes = "登陆验证")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(String logincode, String password, String captcha) throws IOException {
        try {
            Subject subject = ShiroUtils.getSubject();
            // sha256加密
            // password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(logincode, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ApiResponse.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ApiResponse.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ApiResponse.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ApiResponse.error(e.getMessage());
        }
        return ApiResponse.success();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public JSONObject logout() {
        ShiroUtils.logout();
        return ApiResponse.success();
    }
}
