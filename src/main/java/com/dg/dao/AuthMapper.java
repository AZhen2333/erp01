package com.dg.dao;

import com.dg.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {

    /**
     * 根据用户表主键id查询角色列表
     *
     * @param userInfoId
     * @return
     */
    List<Role> getRoleByUserInfoId(@Param("userInfoId") Integer userInfoId);
}
