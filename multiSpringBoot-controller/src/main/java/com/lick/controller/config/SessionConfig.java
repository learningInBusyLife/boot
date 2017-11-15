package com.lick.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @description： session配置类
 * @author: lick
 * @date: 2017年11月15日 10:51
 * @copyright: 版权归 lick 所有
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600*30)
public class SessionConfig {

}
