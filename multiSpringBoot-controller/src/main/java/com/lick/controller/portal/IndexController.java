package com.lick.controller.portal;

import com.alibaba.fastjson.JSONObject;
import com.lick.service.QaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description：
 * @Author: lick
 * @Date: 2017年10月26日 14:53
 * @Copyright: 版权归 lick 所有
 */
@Controller
@EnableAutoConfiguration
public class IndexController {
    @Autowired
    private QaContentService qaContentService;
    @RequestMapping("/")
    String home(HttpServletRequest request, ModelMap map) throws Exception {
        map.put("data", JSONObject.toJSONString(qaContentService.getQaContentAll()));
        return "index";
    }
}
