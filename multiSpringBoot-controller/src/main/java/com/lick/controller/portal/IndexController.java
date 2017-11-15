package com.lick.controller.portal;

import com.lick.utils.SpringRedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Description： 首页
 * @Author: lick
 * @Date: 2017年10月26日 14:53
 * @Copyright: 版权归 lick 所有
 */
@Controller
@EnableAutoConfiguration
public class IndexController {
    private static final String USER_PREFIX="user_info:";

    @Autowired
    private SpringRedisAPI springRedisAPI;

    @RequestMapping(value = "/")
    public String printWelcome() {
        return "forward:/index";
    }
    @RequestMapping("/index")
    String home(HttpServletRequest request, ModelMap map) throws Exception {
        HttpSession session = request.getSession();
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        if(!springRedisAPI.kExists(USER_PREFIX+"11")){
            springRedisAPI.set(USER_PREFIX+"11","lick");
        }
        return "index";
    }
}
