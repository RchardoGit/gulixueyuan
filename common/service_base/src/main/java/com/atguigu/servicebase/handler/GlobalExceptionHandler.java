package com.atguigu.servicebase.handler;

import com.atguigu.commonutils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author konglingyang
 * @date 2022/1/14 13:34
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e) {
        e.getStackTrace();
        return CommonResult.error().message(e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public CommonResult error(ArithmeticException e) {
        e.getStackTrace();
        log.error(e.getMessage());
        return CommonResult.error().message(e.getMessage());
    }

}
