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
}
