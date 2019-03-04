package com.dg.dao;

import com.dg.mybatis.MyMapper;
import com.dg.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    /**
     * 获取角色信息（角色名称模糊查询）
     *
     * @param roleName
     * @return
     */
    List<HashMap<String, Object>> selectRolesDetails(@Param("roleName") String roleName,
                                                     @Param("offset")Integer offset,
                                                     @Param("limit")Integer limit);
}
