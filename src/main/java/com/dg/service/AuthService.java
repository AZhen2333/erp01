package com.dg.service;

import com.dg.pojo.Permission;
import com.dg.pojo.Role;

import java.util.List;

/**
 * 账户角色和权限service
 */
public interface AuthService {

    /**
     * 根据用户表主键id查询角色列表
     *
     * @param userInfoId
     * @return
     */
    public List<Role> getRoleByUserInfoId(Integer userInfoId);

    /**
     * 根据角色ID查找权限树列表
     *
     * @param roleId
     * @return
     */
    public List<Permission> findPermsByRoleId(Integer roleId);
}