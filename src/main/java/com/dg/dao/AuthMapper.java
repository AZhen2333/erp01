package com.dg.dao;

import com.dg.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {

    /**
     * 根据角色id查询角色列表
     *
     * @param roleIds
     * @return
     */
    List<Role> getRoleListById(@Param("roleIds") List<Integer> roleIds);
}
