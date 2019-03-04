package com.dg.service;

import com.dg.pojo.UserInfo;
import com.dg.shiro.ShiroUser;

public interface UserInfoService {

    /**
     * 根据帐号查询用户数据
     *
     * @param username
     * @return
     */
    public UserInfo findUserByUsername(String username);

    /**
     * user转换ShiroUser
     * @param user
     * @return
     */
    public ShiroUser shiroUser(UserInfo user);

}
