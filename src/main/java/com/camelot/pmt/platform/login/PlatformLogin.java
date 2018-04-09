package com.camelot.pmt.platform.login;

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

import com.alibaba.fastjson.JSONObject;
import com.camelot.pmt.platform.common.ApiResponse;
import com.camelot.pmt.platform.shiro.PlatformShiroUtils;

@ResponseBody
public class PlatformLogin {
	
	/**
	 * 登陆
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONObject login(String username, String password) {
		try{
			Subject subject = PlatformShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return ApiResponse.error();
		}catch (IncorrectCredentialsException e) {
			return ApiResponse.error();
		}catch (LockedAccountException e) {
			return ApiResponse.error();
		}catch (AuthenticationException e) {
			return ApiResponse.error();
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
