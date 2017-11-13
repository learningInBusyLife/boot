package com.lick.service.impl;

import com.lick.dal.UserInfoDao;
import com.lick.model.entity.UserInfo;
import com.lick.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description：
 * @author: lick@hsyuntai.com
 * @date: 2017年11月13日 15:46
 * @copyright: 版权归hsyuntai 所有
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoByUserCode(String userCode) {
        return userInfoDao.getUserInfoByUserCode(userCode);
    }
}
