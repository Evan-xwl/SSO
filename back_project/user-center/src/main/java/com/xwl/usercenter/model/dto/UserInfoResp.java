package com.xwl.usercenter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ruoling
 * @date 2024/10/29 10:43:02
 * @description
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResp {

    private String msg;

    private Object data;

    private int code;


}
