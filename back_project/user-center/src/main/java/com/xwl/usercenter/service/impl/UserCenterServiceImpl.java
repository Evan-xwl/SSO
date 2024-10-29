package com.xwl.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwl.usercenter.constant.RegisterEnum;
import com.xwl.usercenter.constant.UserConstants;
import com.xwl.usercenter.mapper.UserCenterMapper;
import com.xwl.usercenter.model.domain.User;
import com.xwl.usercenter.service.UserCenterService;
import com.xwl.usercenter.utils.StringMatchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
* @author ruoling
* @description 针对表【user_center(用户表)】的数据库操作Service实现
* @createDate 2024-10-28 19:34:44
*/
@Service
public class UserCenterServiceImpl extends ServiceImpl<UserCenterMapper, User>
    implements UserCenterService {

    @Resource
    private UserCenterMapper userCenterMapper;

    /**
     * 用户注册接口
     * @param userAccount 用户账号
     * @param password 用户密码
     * @param checkPassword 确认密码
     * @return 正常返回创建的用户id
     */
    @Override
    public long registerUser(String userAccount, String password, String checkPassword) {
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)
            || userAccount.length() < 5
            || password.length() < 6
            || checkPassword.length() < 6
            || !password.equals(checkPassword)
        ) {
            return RegisterEnum.PARAM_INVALID.getValue();
        }

        if (StringMatchUtils.containsSpecialChar(userAccount)) {
            return RegisterEnum.PARAM_INVALID.getValue();
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        User queryUser = this.getOne(queryWrapper);
        if (queryUser != null) {
            return RegisterEnum.USER_EXITS.getValue();
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex((UserConstants.SALT + password).getBytes(StandardCharsets.UTF_8));
        User user = User.builder()
                .userAccount(userAccount)
                .userName(userAccount)
                .userPassword(encryptedPassword)
                .build();
        boolean saveRes = this.save(user);
        if (!saveRes) {
            return RegisterEnum.SAVE_FAIL.getValue();
        }
        return user.getId();
    }

    @Override
    public User loginUser(String userAccount, String password) {
        if (StringUtils.isAnyBlank(userAccount, password)
            || userAccount.length() < 5
            || password.length() < 6
        ) {
            return null;
        }

        String encryptedPassword = DigestUtils.md5DigestAsHex((UserConstants.SALT + password).getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptedPassword);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            return null;
        }
        User userView = User.builder()
                .userName(user.getUserName())
                .userAccount(user.getUserAccount())
                .gender(user.getGender())
                .userRole(user.getUserRole())
                .avtarUrl(user.getAvtarUrl())
                .build();
        return userView;
    }


}




