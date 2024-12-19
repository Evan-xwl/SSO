package com.xwl.usercenter.controller;

import com.xwl.usercenter.constant.RegisterEnum;
import com.xwl.usercenter.constant.RespConstants;
import com.xwl.usercenter.model.domain.User;
import com.xwl.usercenter.model.dto.UserInfoResp;
import com.xwl.usercenter.model.vo.UserInfoReq;
import com.xwl.usercenter.service.UserCenterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @CrossOrigin
    public UserInfoResp userRegister(@RequestBody UserInfoReq userInfoReq) {
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
        if (userId < 0) {
            return UserInfoResp.builder()
                    .msg(RegisterEnum.getEnumByValue((int) userId).getDesc())
                    .code(RespConstants.PARAM_INVALID)
                    .build();
        }
        return UserInfoResp.builder()
                .data(userId)
                .msg("用户id")
                .code(RespConstants.SUCCESS)
                .build();
    }


    @PostMapping("/login")
    @CrossOrigin
    public UserInfoResp userLogin(@RequestBody UserInfoReq userInfoReq,
                                  HttpServletResponse response,
                                  @RequestParam(value = "source", required = false) String source) {
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
        // 登录成功,判断登录来源，返回不同的重定向地址
        UserInfoResp userInfoResp = UserInfoResp.builder()
                .data(user)
                .code(RespConstants.SUCCESS)
                .build();
        if (StringUtils.isNotBlank(source)) {
            if ("edk".equals(source)) {
                userInfoResp.setRedirectUrl("http://localhost:9981");
            } else if ("eml".equals(source)) {
                userInfoResp.setRedirectUrl("http://localhost:9982");
            }
        }
        return userInfoResp;
    }
}
