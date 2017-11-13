package com.lick.dal;

import com.lick.model.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @description：
 * @author: lick@hsyuntai.com
 * @date: 2017年11月13日 15:30
 * @copyright: 版权归hsyuntai 所有
 */
@Repository
public interface UserInfoDao {
    /**
    * @description: 根据用户code获取用户信息
    * @method: getUserInfoByUserCode
    * @params: [userCode]
    * @returnType: com.lick.model.entity.UserInfo
    * @author: lick
    * @date: 2017/11/13 15:33
    * @copyright: 版权归 lick 所有
     */
    @Select("select * from user_info where user_code = #{userCode}")
    public UserInfo getUserInfoByUserCode(@Param(value = "userCode") String userCode);
}
