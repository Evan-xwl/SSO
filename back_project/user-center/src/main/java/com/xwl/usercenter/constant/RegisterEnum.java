package com.xwl.usercenter.constant;

/**
 * @author ruoling
 * @date 2024/10/28 22:27:16
 * @description
 */
public enum RegisterEnum {
    PARAM_INVALID(-1, "注册参数不合法"),
    USER_EXITS(-2, "用户已存在"),
    SAVE_FAIL(-3, "注册用户失败");
    private int value;
    private String desc;


    RegisterEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
