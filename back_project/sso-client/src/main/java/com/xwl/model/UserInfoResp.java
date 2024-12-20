package com.xwl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResp {

    private String msg;

    private Object data;

    private int code;

    private String redirectUrl;
}