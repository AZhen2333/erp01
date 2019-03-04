package com.dg.controller;

import com.dg.annotion.Permission;
import com.dg.exception.BizExceptionEnum;
import com.dg.exception.ServiceException;
import com.dg.factory.ConstantFactory;
import com.dg.pojo.Role;
import com.dg.service.RoleService;
import com.dg.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static String PREFIX = "/system/role";

    @Autowired
    private RoleService roleService;

    /**
     * 跳转角色页面
     *
     * @return
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "role.html";
    }

    /**
     * 跳转到添加角色
     *
     * @return
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    @Permission
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        // 获取角色信息
        Role role = roleService.selectById(roleId);
        model.addAttribute(role);
        // 获取父角色名称
        model.addAttribute("pRoleName", ConstantFactory.me().getSingleRoleName(role.getPid()));
        // TODO 获取部门名称

        return PREFIX + "/role_edit.html";
    }
}

