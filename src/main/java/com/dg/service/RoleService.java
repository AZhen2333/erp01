package com.dg.service;

import com.dg.pojo.Role;
import com.dg.pojo.vo.PageResult;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;

import java.util.HashMap;
import java.util.List;

public interface RoleService {

    /**
     * 获取角色信息（角色名称模糊查询）
     *
     * @param roleName
     * @return
     */
    public PageResult selectRoles(String roleName, Integer offset, Integer limit);

    public Role selectById(Integer roleId);
}
