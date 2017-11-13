package com.lick.service;

import com.lick.model.entity.UserInfo;

/**
 * @description：
 * @author: lick@hsyuntai.com
 * @date: 2017年11月13日 15:45
 * @copyright: 版权归hsyuntai 所有
 */
public interface UserInfoService {

    public UserInfo getUserInfoByUserCode(String userCode);
}
