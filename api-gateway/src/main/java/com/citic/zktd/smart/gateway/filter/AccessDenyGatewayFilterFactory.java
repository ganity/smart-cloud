package com.citic.zktd.smart.gateway.filter;

import com.citic.zktd.smart.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;


/**
 *
 */
@Component
public class AccessDenyGatewayFilterFactory extends AbstractGatewayFilterFactory {

    private static final Logger log = LoggerFactory.getLogger(AccessDenyGatewayFilterFactory.class);

    public GatewayFilter apply() {
        return apply(o -> {
        });
    }

    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String userName = exchange.getAttribute("user_name");
            //TODO 使用userName从redis等查询权限信息,暂时从attributes中获取
            List<String> authorities = exchange.getAttribute("authorities");
            List<String> resources = exchange.getAttribute("resources");
            String path = exchange.getRequest().getPath().value();
            System.err.println(resources);
            System.err.println(authorities);
            System.err.println(userName);
            System.err.println(path);
            // 匹配当前请求地址与权限配置信息
            PathMatcher matcher = new AntPathMatcher();
            boolean access = false;
            for (int i = 0; i < resources.size(); i++) {
                if (matcher.match(resources.get(i), path)) {
                    access = true;
                    break;
                }
            }
            if (!access) {
                //没有访问权限
                log.info("Access Denied for access path '{}' not matched the resources which the user '{}' had.", path, userName);
                Result result = Result.ofFailed(HttpStatus.FORBIDDEN, "没有访问权限");
                return FilterUtil.failed(exchange, result, HttpStatus.FORBIDDEN);
            }
            return chain.filter(exchange);
        };
    }
}
