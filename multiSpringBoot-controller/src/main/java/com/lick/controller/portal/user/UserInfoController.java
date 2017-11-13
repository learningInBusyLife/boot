package com.lick.controller.portal.user;

import com.lick.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description： 用户信息接口
 * @author: lick
 * @date: 2017年11月13日 15:43
 * @copyright: 版权归 lick 所有
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/p/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/user_info")
    public String getUserInfo(ModelMap map){
        map.put("userInfo",userInfoService.getUserInfoByUserCode("11"));
        return "user/userInfo";
    }
}
