package com.xwl.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xwl.usercenter.model.domain.User;

/**
* @author ruoling
* @description 针对表【user_center(用户表)】的数据库操作Service
* @createDate 2024-10-28 19:34:44
*/
public interface UserCenterService extends IService<User> {

    long registerUser(String userAccount, String password, String checkPassword);

    User loginUser(String userAccount, String password);
}
