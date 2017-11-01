package com.lick.controller.config;

import com.lick.utils.ErrorCode;
import com.lick.utils.ResultObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description：
 * @Author: lick@hsyuntai.com
 * @Date: 2017年11月01日 13:49
 * @Copyright: 版权归hsyuntai 所有
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //设置此handler处理所有异常
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public ResultObject defaultErrorHandler(HttpServletRequest request,Exception e) throws Exception{
        return ResultObject.create(request.getRequestURI(), ErrorCode.ERROR_SYS_1001,"服务器内部错误");
    }
}
