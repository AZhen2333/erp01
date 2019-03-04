package com.dg.service.impl;

import com.dg.dao.RoleMapper;
import com.dg.pojo.Role;
import com.dg.pojo.vo.PageResult;
import com.dg.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult selectRoles(String roleName, Integer offset, Integer limit) {
        List<HashMap<String, Object>> list = roleMapper.selectRolesDetails(roleName, (offset - 1) * limit, limit);
        long totalRows = 0;
        if (null != list && list.size() > 0) {
            HashMap<String, Object> map = list.get(0);
            totalRows = (Long) map.get("totalRows");
        }
        return new PageResult(offset, totalRows, list);
    }

    @Override
    public Role selectById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
