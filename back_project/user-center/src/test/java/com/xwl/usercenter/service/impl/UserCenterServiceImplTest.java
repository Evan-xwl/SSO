package com.xwl.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xwl.usercenter.model.domain.User;
import com.xwl.usercenter.utils.StringMatchUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ruoling
 * @date 2024/10/28 19:43:34
 * @description
 */
@SpringBootTest
class UserCenterServiceImplTest {

    @Resource
    private UserCenterServiceImpl userCenterService;

    @Test
    void testSelectData () {
        List<User> list = userCenterService.list();
        Assertions.assertNotNull(list);
    }

    @Test
    void testRegex() {
        String userAccount = "dfgh;9(√";
        boolean match = StringMatchUtils.containsSpecialChar(userAccount);
        Assertions.assertEquals(true, match);
    }

    @Test
    void testRegisterUserParamSuccess () {
        long res = userCenterService.registerUser("abc1234", "12345678", "12345678");
        System.out.println(res);
    }

    @Test
    void testLogicDelete () {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "Evan");
        boolean remove = userCenterService.remove(queryWrapper);
        System.out.println(remove);
    }
}