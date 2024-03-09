package com.einmeer.config;

import com.einmeer.vo.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 芊嵛
 * @date 2024/3/6
 */
@RestControllerAdvice
public class DefaultException {
    @ExceptionHandler
    public ResultJson handler(Exception ex){
        ex.printStackTrace();
        if (ex instanceof MyException){
            return ResultJson.failed(ex.getMessage());
        }
        return ResultJson.failed("系统异常："+ex.getMessage());
    }
}
