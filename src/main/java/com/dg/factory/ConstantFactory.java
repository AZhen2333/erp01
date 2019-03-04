package com.dg.factory;

import com.dg.dao.RoleMapper;
import com.dg.pojo.Role;
import com.dg.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 常量生产工厂
 */
@Component
public class ConstantFactory {

    private static ApplicationContext applicationContext;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取ConstantFactory的bean
     *
     * @return
     */
    public static ConstantFactory me() {
        return (ConstantFactory) applicationContext.getBean("constantFactory");
    }

    /**
     * 根绝角色id获取角色名称
     *
     * @param roleId
     * @return
     */
    public String getSingleRoleName(Integer roleId) {
        if (0 == roleId || null == roleId) {
            return "--";
        }
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (ToolUtil.isNotEmpty(role) && ToolUtil.isNotEmpty(role.getRoleName())) {
            return role.getRoleName();
        }
        return "";
    }
}
