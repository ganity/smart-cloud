package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/11 10:14
 */
@Component
@FeignClient(value = "example-webflux-provider")
public interface AccountApi {
    @GetMapping("/account/flux/{customer}")
    User findByAccount(@PathVariable("customer") String customerId);
    @GetMapping("/account/{id}")
    User findById(@PathVariable("id") String id);
}
