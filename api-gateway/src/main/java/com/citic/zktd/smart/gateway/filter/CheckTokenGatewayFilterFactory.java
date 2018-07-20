package com.citic.zktd.smart.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.citic.zktd.smart.bean.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CheckTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckTokenGatewayFilterFactory.Config> {
    public static final String ACCESS_TOKEN_NAME = "access_token";

    private static final Logger log = LoggerFactory.getLogger(CheckTokenGatewayFilterFactory.class);

    private volatile JWTVerifier verifier;

    public CheckTokenGatewayFilterFactory() {
        super(CheckTokenGatewayFilterFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("verifierkey"/*, "value"*/);
    }

    @Override
    public GatewayFilter apply(CheckTokenGatewayFilterFactory.Config config) {
        // grab configuration from Config object
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            //If you want to build a "pre" filter you need to manipulate the
            //request before calling change.filter
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            String access_token = queryParams.getFirst(ACCESS_TOKEN_NAME);
            if (StringUtils.isEmpty(access_token)) {
                return FilterUtil.failed(exchange, Result.ofFailed(HttpStatus.UNAUTHORIZED, "token is empty"), HttpStatus.UNAUTHORIZED);
            }
            try {
                JWTVerifier verifier = getVerifier(config.verifierkey);
                DecodedJWT jwt = verifier.verify(access_token);
                Map<String, Claim> claims = jwt.getClaims();
                //校验访问权限
                //TODO 此处是从jwt中获取的访问权限信息,存在的问题是当权限被修改后不能及时更新, 可以每次从redis等缓存中动态获取
                String userName = claims.get(FilterUtil.USER_NAME).asString();
                exchange.getAttributes().put(FilterUtil.USER_NAME, userName);

                //TODO 从redis等缓存中动态获取,就不需要再放入到Attributes中
                List<String> authorities = claims.get("authorities").asList(String.class);
                System.err.println(authorities);
                List<String> resources = claims.get("resources").asList(String.class);
                exchange.getAttributes().put("authorities", authorities);
                exchange.getAttributes().put("resources", resources);

//                String path = request.getPath().value();
//                System.err.println(resources);
//                System.err.println(path);
//                PathMatcher matcher = new AntPathMatcher();
//                boolean access = false;
//                for (int i = 0; i < resources.size(); i++) {
//                    if (matcher.match(resources.get(i), path)) {
//                        access = true;
//                        break;
//                    }
//                }
//                if (!access) {
//                    //没有访问权限
////                    ServerHttpResponse response = exchange.getResponse();
////                    response.getHeaders().add("fuck", "111111");
////                    response.setStatusCode(HttpStatus.FORBIDDEN);
////                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//                    Result result = Result.ofFailed(HttpStatus.FORBIDDEN, "没有访问权限");
////                    ObjectMapper objectMapper = new ObjectMapper();
////                    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
////                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
////                    String rs = "{\"status\":401}";
////                    try {
////                        rs = objectMapper.writeValueAsString(result);
////                    } catch (JsonProcessingException e1) {
////                        e1.printStackTrace();
////                    }
////                    DataBuffer bodyDataBuffer = response.bufferFactory().wrap(rs.getBytes());
////                    return response.writeWith(Mono.just(bodyDataBuffer));
//                    return FilterUtil.failed(exchange, result, HttpStatus.FORBIDDEN);
//                }

            } catch (JWTVerificationException e) {
                //Invalid token
//                e.printStackTrace();
                log.info("token check failed for {}", e.getMessage());
//                ServerHttpResponse response = exchange.getResponse();
//                response.getHeaders().add("fuck", "111111");
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                Result result = null;
                if (e instanceof TokenExpiredException) {
                    result = Result.ofFailed(Result.ResultCode.AUTHENTICATED_DENIED_ERROR_TOKEN_TIMEOUT, e.getMessage());
                } else {
                    result = Result.ofFailed(HttpStatus.UNAUTHORIZED, e.getMessage());
                }
//                ObjectMapper objectMapper = new ObjectMapper();
//                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//                String rs = "{\"status\":401}";
//                try {
//                    rs = objectMapper.writeValueAsString(result);
//                } catch (JsonProcessingException e1) {
//                    e1.printStackTrace();
//                }
//                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(rs.getBytes());
//                return response.writeWith(Mono.just(bodyDataBuffer));
                return FilterUtil.failed(exchange, result, HttpStatus.UNAUTHORIZED);

            }
//            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            //use builder to manipulate the request
            return chain.filter(exchange.mutate().request(request).build());
        };
    }



    private synchronized JWTVerifier getVerifier(String verifierKey) {
        if (null == verifier) {
            verifier = JWT.require(Algorithm.HMAC256(verifierKey))
                    .build();
        }
        return verifier;
    }

    public static class Config {
        private String verifierkey;

        public Config() {
        }

        public String getVerifierkey() {
            return verifierkey;
        }

        public void setVerifierkey(String verifierkey) {
            this.verifierkey = verifierkey;
        }
    }
}