package com.dg.service.impl;

import com.dg.dao.AuthMapper;
import com.dg.dao.PermissionMapper;
import com.dg.pojo.Permission;
import com.dg.pojo.Role;
import com.dg.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> getRoleByUserInfoId(Integer userInfoId) {
        return authMapper.getRoleByUserInfoId(userInfoId);
    }

    @Override
    public List<Permission> findPermsByRoleId(Integer roleId){
        return permissionMapper.findPermsByRole(roleId);
    }
}
