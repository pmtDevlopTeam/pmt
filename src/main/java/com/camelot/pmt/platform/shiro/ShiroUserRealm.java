package com.camelot.pmt.platform.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camelot.pmt.platform.model.User;
import com.camelot.pmt.platform.service.UserService;

/**
 *
 * @author gnerv
 * @Description 用户登录认证和权限认证
 * @date 2018年4月18日
 */
@Component
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /*
     * @Autowired private MenuService menuService;
     */

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Set<String> permsSet = new HashSet<String>();
        permsSet.add("platform:menu:queryAllMenu");
        permsSet.add("platform:menu:queryAllMenu");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);

        /*
         * SysUser user = (SysUser) principals.getPrimaryPrincipal(); String userId =
         * user.getUserId();
         * 
         * // 用户权限列表 Set<String> permsSet = sysMenuService.getUserPermissions(userId);
         * SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         * info.setStringPermissions(permsSet); return info;
         */
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String logincode = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User userModel = new User();
        userModel.setLoginCode(logincode);
        userModel.setPassword(password);
        User user = userService.queryLoginCodeAndPassword(userModel);

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 账号停用
        if ("1".equals(user.getState())) {
            throw new LockedAccountException("账号已被停用,请联系管理员");
        }

        // 账号锁定
        if ("2".equals(user.getState())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        ShiroUtils.setSessionAttribute("user", user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(logincode, password, getName());
        return info;
    }

}
