package com.camelot.pmt.platform.login;

import java.io.IOException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.shiro.PlatformShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@ResponseBody
@RestController
@Api(value = "用户登陆接口", description = "用户登陆接口")
public class PlatformLogin {
	
	/**
	 * 登陆
	 */
	@ApiOperation(value="登陆接口", notes="登陆验证")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONObject login(String logincode, String password, String captcha) throws IOException {
		try{
			Subject subject = PlatformShiroUtils.getSubject();
			//sha256加密
			//password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(logincode, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return ApiResponse.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return ApiResponse.error(e.getMessage());
		}catch (LockedAccountException e) {
			return ApiResponse.error(e.getMessage());
		}catch (AuthenticationException e) {
			return ApiResponse.error(e.getMessage());
		}
		return ApiResponse.success();
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public JSONObject logout() {
		PlatformShiroUtils.logout();
		return ApiResponse.success();
	}
}
