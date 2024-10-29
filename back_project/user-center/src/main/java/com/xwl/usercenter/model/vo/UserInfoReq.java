package com.xwl.usercenter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ruoling
 * @date 2024/10/29 10:50:03
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoReq {

    private String userAccount;

    private String password;

    private String checkPassword;

}
