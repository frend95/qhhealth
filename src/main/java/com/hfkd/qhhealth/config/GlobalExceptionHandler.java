package com.hfkd.qhhealth.config;

import com.hfkd.qhhealth.common.exception.MyException;
import com.hfkd.qhhealth.common.util.RspUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 全局异常处理
 * @author hexq
 * @date 2017/11/15 14:02
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object allExceptionHandler(Exception exception) {
        exception.printStackTrace();
        Map<String, Object> exceptionMap = RspUtil.error();
        // 如果是自定义异常那么就把异常信息放到msg中
        if (exception.getClass().equals(MyException.class)) {
            exceptionMap.put("msg", exception.getMessage());
        }
        return exceptionMap;
    }

}
