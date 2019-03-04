package com.dg.shiro;

import com.dg.pojo.Permission;
import com.dg.pojo.Role;
import com.dg.pojo.UserInfo;
import com.dg.service.AuthService;
import com.dg.service.UserInfoService;
import com.dg.service.impl.AuthServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ShiroRealm认证实体类
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthService authService;

    /**
     * 授予角色和权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 添加权限 和 角色信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        UserInfo user = (UserInfo) subject.getPrincipal();
        Integer userInfoId = user.getId();
        // 查询用户的角色，根据角色查询权限
        List<Role> roles = authService.getRoleByUserInfoId(userInfoId);
        if (null != roles && roles.size() > 0) {
            for (Role role : roles) {
                authorizationInfo.addRole(role.getCode());
                // 角色对应的权限数据
                List<Permission> perms = this.authService.findPermsByRoleId(role.getId());
                if (null != perms && perms.size() > 0) {
                    // 授权角色下所有权限
                    for (Permission perm : perms) {
                        authorizationInfo.addStringPermission(perm.getCode());
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 用于存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        System.out.println("=================" + token.getPassword().toString());
        UserInfo user = userInfoService.findUserByUsername(username);
        if (user == null) {
            // 用户不存在
            return null;
        } else {
//            return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
            ShiroUser shiroUser = userInfoService.shiroUser(user);
            System.out.println("=================" + user.getPassword());
            return new SimpleAuthenticationInfo(shiroUser, DigestUtils.md5Hex(user.getPassword()), getName());
        }
    }
}
