package com.dg.shiro.service;

/**
 * 权限校验接口
 */
public interface PermissionCheckService {

    /**
     * 检查当前登录用户是否拥有指定的角色访问权
     *
     * @param permissions
     * @return
     */
    boolean check(Object[] permissions);

    /**
     * 检查当前登录用户是否拥有当前请求的servlet的权限
     *
     * @return
     */
    public boolean checkAll();
}
