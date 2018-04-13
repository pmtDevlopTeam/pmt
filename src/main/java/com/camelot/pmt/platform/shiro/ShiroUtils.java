package com.camelot.pmt.platform.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Shiro工具类
 * 
 */
@Component
public class ShiroUtils {
    private static Logger logger = LoggerFactory.getLogger(ShiroUtils.class);

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

/*    
     * @SuppressWarnings("unchecked") public static SysUser getUserObject(){ try{
     * return (SysUser) SecurityUtils.getSubject().getPrincipal(); }catch(
     * ClassCastException cce){ //logger.warn("生产环境中请去掉spring devtool");
     * SecurityUtils.getSubject().logout(); throw new ShiroException(); }
     * 
     * 
     * }
     */

}
