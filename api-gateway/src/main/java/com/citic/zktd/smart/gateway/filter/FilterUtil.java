package com.citic.zktd.smart.gateway.filter;

import com.citic.zktd.smart.bean.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/20 16:13
 */
public class FilterUtil {
    public static final String USER_NAME = "user_name";
    public static Mono<Void> failed(ServerWebExchange exchange, Result result, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("fuck", "111111");
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String rs = "{\"status\":401}";
        try {
            rs = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(rs.getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}
