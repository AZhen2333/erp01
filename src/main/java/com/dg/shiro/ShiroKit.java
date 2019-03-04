package com.dg.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 */
public class ShiroKit {

    private static final String NAMES_DELIMETER = ",";

    /**
     * 获取当前 Subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取封装的 ShiroUser
     *
     * @return
     */
    public static ShiroUser getShiroUser() {
        return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * 验证当前用户是否属于以下任意一个角色。
     *
     * @param roleNames
     * @return
     */
    public static boolean hasAnyRoles(String roleNames) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(NAMES_DELIMETER)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }
}
