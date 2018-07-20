package com.citic.zktd.smart.controller;

import com.citic.zktd.smart.bean.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/19 11:49
 */
@ControllerAdvice
public class ResultControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof LinkedHashMap) {
            LinkedHashMap<String, Object> map = (LinkedHashMap)o;
            if (HttpStatus.OK.value() != (int)map.get("status"))
                return o;
        }
        return Result.ofSuccess(o);
    }
}
