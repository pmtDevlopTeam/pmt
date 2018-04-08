package com.camelot.pmt.platform.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 认证
 * 
 * 
 * 
 * @date 2016年11月10日 上午11:55:49
 */
@Component
public class PlatformUserRealm extends AuthorizingRealm {
/*	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private ISysMenuService sysMenuService;*/

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		
/*		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		String userId = user.getUserId();

		// 用户权限列表
		Set<String> permsSet = sysMenuService.getUserPermissions(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;*/
		return null;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String loginName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		/*// 查询用户信息
		SysUser user = sysUserService.selectUserByLoginName(loginName);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
				password, getName());
		return info;*/
		return null;
	}

}
