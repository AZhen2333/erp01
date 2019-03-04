package com.dg.service.impl;

import com.dg.dao.RoleMapper;
import com.dg.pojo.Role;
import com.dg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
