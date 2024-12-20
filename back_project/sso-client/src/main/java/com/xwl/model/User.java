package com.xwl.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avtarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户状态-正常（0）
     */
    private Integer userStatus;

    /**
     * 普通用户（0），管理员（1）
     */
    private Integer userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除--0存在，1删除
     */
    private Integer isDelete;
}