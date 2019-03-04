package com.dg.shiro.service.impl;

import com.dg.shiro.ShiroKit;
import com.dg.shiro.ShiroUser;
import com.dg.shiro.service.PermissionCheckService;

import java.util.ArrayList;
import java.util.Iterator;6
import java.util.List;

public class PermissionCheckServiceImpl implements PermissionCheckService {

    /**
     * 检查当前登录用户是否拥有指定的角色访问权
     *
     * @param permissions
     * @return
     */
    @Override
    public boolean check(Object[] permissions) {
        ShiroUser shiroUser = ShiroKit.getShiroUser();
        if (null == shiroUser) {
            return false;
        }
        List<String> roleNames = new ArrayList<>();
        for (Object obj : permissions) {
            if (null != obj) {
                roleNames.add((String) obj);
            }
        }
        if (null != roleNames && roleNames.size() > 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = roleNames.iterator();
            if (iterator.hasNext()) {
                sb.append(",").append(iterator.next());
            }
            // 是否角色
            if (ShiroKit.hasAnyRoles(sb.toString())) {
                return true;
            }
        }
        return false;
    }
}
