package com.dg.shiro.service.impl;

import com.dg.listener.ConfigListener;
import com.dg.shiro.ShiroKit;
import com.dg.shiro.ShiroUser;
import com.dg.shiro.service.PermissionCheckService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
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

    /**
     * 检查当前登录用户是否拥有当前请求的servlet的权限
     *
     * @return
     */
    @Override
    public boolean checkAll() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        ShiroUser user = ShiroKit.getShiroUser();
        if (null == user) {
            return false;
        }
        String requestURI = request.getRequestURI().replaceFirst(ConfigListener.getConf().get("contextPath"), "");
        String[] str = requestURI.split("/");
        if (str.length > 3) {
            requestURI = "/" + str[1] + "/" + str[2];
        }
        if (ShiroKit.hasPermission(requestURI)) {
            return true;
        }
        return false;
    }
}
