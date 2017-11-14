package com.lick.controller.portal;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description： 首页
 * @Author: lick
 * @Date: 2017年10月26日 14:53
 * @Copyright: 版权归 lick 所有
 */
@Controller
@EnableAutoConfiguration
public class IndexController {

    @RequestMapping(value = "/")
    public String printWelcome() {
        return "forward:/index";
    }
    @RequestMapping("/index")
    String home(HttpServletRequest request, ModelMap map) throws Exception {
        return "index";
    }
}
