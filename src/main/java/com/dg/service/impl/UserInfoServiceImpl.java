package com.dg.service.impl;

import com.dg.dao.UserInfoMapper;
import com.dg.pojo.UserInfo;
import com.dg.service.UserInfoService;
import com.dg.shiro.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserByUsername(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setStatus(1);
        return userInfoMapper.selectOne(userInfo);
    }

    @Override
    public ShiroUser shiroUser(UserInfo user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setUsername(user.getUsername());
        shiroUser.setDeptId(user.getDeptid());
        // TODO 部门名称处理
//        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(user.getName());

        List<Integer> roleList = strToIntArray(user.getRoleid());

        // TODO List<String> roleNameList = new ArrayList<String>();
//        for (int roleId : roleArray) {
//            roleList.add(roleId);
//            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
//        }
        shiroUser.setRoleList(roleList);
        // TODO shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }


    public List<Integer> strToIntArray(String strs){
        List<Integer> list = new ArrayList<>();
        if(strs.length() == 1){
            list.add(Integer.valueOf(strs));
        }else if(strs.contains(",")){
            String[] strArray = strs.split(",");
            for (String str : strArray) {
                list.add(Integer.valueOf(str));
            }
        }
        return list;
    }

}
