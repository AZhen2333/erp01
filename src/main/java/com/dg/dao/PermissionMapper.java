package com.dg.dao;

import com.dg.mybatis.MyMapper;
import com.dg.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {
    /**
     * 根据角色ID查找权限树列表
     *
     * @param roleId
     * @return
     */
    List<Permission> findPermsByRole(@Param("roleId") Integer roleId);
}
