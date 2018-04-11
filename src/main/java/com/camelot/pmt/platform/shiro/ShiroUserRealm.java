package com.camelot.pmt.platform.shiro;

import com.camelot.pmt.platform.common.ExecuteResult;
import com.camelot.pmt.platform.model.UserModel;
import com.camelot.pmt.platform.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 认证
 */
@Component
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

/*    @Autowired
    private MenuService menuService;*/

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Set<String> permsSet = new HashSet<String>();
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
        System.out.println(logincode + "---" + password);
        UserModel userModel = new UserModel();
        userModel.setLoginCode(logincode);
        userModel.setPassword(password);
        ExecuteResult<UserModel> queryLoginCodeAndPassword = userService.queryLoginCodeAndPassword(userModel);
        UserModel user = queryLoginCodeAndPassword.getResult();

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

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(logincode, password, getName());
        return info;
    }

}
