package com.xwl.usercenter.controller;

import com.xwl.usercenter.constant.RespConstants;
import com.xwl.usercenter.model.domain.User;
import com.xwl.usercenter.model.dto.UserInfoResp;
import com.xwl.usercenter.model.vo.UserInfoReq;
import com.xwl.usercenter.service.UserCenterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ruoling
 * @date 2024/10/29 10:41:03
 * @description 包含用户注册，登录等功能
 */
@Slf4j
@RestController
@RequestMapping("/usercenter")
public class UserCenterController {

    @Resource
    private UserCenterService userCenterService;

    @PostMapping("/register")
    public UserInfoResp userRegister (@RequestBody UserInfoReq userInfoReq) {
        if (userInfoReq == null) {
            return UserInfoResp.builder()
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }
        String userAccount = userInfoReq.getUserAccount();
        String password = userInfoReq.getPassword();
        String checkPassword = userInfoReq.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)) {
            return UserInfoResp.builder()
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }
        long userId = userCenterService.registerUser(userAccount, password, checkPassword);
        return UserInfoResp.builder()
                .data(userId)
                .msg("用户id")
                .code(RespConstants.SUCCESS)
                .build();
    }


    @PostMapping("/login")
    public UserInfoResp userLogin (@RequestBody UserInfoReq userInfoReq) {
        if (userInfoReq == null) {
            return UserInfoResp.builder()
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }
        String userAccount = userInfoReq.getUserAccount();
        String password = userInfoReq.getPassword();
        if (StringUtils.isAnyBlank(userAccount, password)) {
            return UserInfoResp.builder()
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }

        User user = userCenterService.loginUser(userAccount, password);
        if (user == null) {
            return UserInfoResp.builder()
                    .msg("用户名或密码错误")
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }
        return UserInfoResp.builder()
                .data(user)
                .code(RespConstants.SUCCESS)
                .build();
    }
}