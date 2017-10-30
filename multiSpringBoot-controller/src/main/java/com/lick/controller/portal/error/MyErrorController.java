package com.lick.controller.portal.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description：
 * @Author: lick@hsyuntai.com
 * @Date: 2017年10月27日 16:57
 * @Copyright: 版权归hsyuntai 所有
 */
@Controller
@RequestMapping(value = "error")
public class MyErrorController {
    @RequestMapping(produces = "text/html", value = "404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        return new ModelAndView("error/404");
    }
    @RequestMapping(produces = "text/html", value = "500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        return new ModelAndView("error/500");
    }
    /**
     * 获取错误编码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public String getErrorPath() {
        return "";
    }
}
