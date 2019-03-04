package com.dg.controller;

import com.dg.entity.ResponseResult;
import com.dg.entity.UserDTO;
import com.dg.pojo.UserInfo;
import com.dg.service.UserInfoService;
import com.dg.utils.IStatusMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     *
     * @param user
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(UserDTO user,
                                @RequestParam(value = "remrmberMe", required = false) boolean rememberMe) {
        // 结果集
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
        if (null == user) {
            responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
            responseResult.setMessage("请求参数有误，请您稍后再试");
            return responseResult;
        }
        // 用户是否存在
        UserInfo existUser = userInfoService.findUserByUsername(user.getUsername());
        if (null == existUser) {
            responseResult.setMessage("该用户不存在，请您联系管理员");
            return responseResult;
        } else if (existUser.getStatus() == 2) {
            responseResult.setMessage("该用户已冻结，请您联系管理员");
            return responseResult;
        }
        try {
            // 用户登录
            // 1、 封装用户名、密码、是否记住我到token令牌对象 [支持记住我]
            System.out.println("=================" + user.getPassword());
            AuthenticationToken token = new UsernamePasswordToken(
                    user.getUsername(), DigestUtils.md5Hex(user.getPassword()), rememberMe);
            // 2、 Subject调用login
            Subject subject = SecurityUtils.getSubject();
            // 调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中
            subject.login(token);
            // 登录验证成功
            responseResult.setCode(IStatusMessage.SystemStatus.SUCCESS.getCode());
        } catch (UnknownAccountException uae) {
            responseResult.setMessage("该用户不存在，请您联系管理员");
        } catch (AuthenticationException ae) {
            responseResult.setMessage("用户名或密码不正确");
        } catch (Exception e) {
            responseResult.setMessage("用户登录失败，请您稍后再试");
        }
        // 返回结果集
        return responseResult;
    }
}
