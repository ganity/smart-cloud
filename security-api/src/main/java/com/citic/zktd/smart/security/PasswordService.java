package com.citic.zktd.smart.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/6 18:31
 */

@FeignClient(value = "auth-server")
public interface PasswordService {

    @GetMapping("/password/encode")
    String getPassword(@RequestParam("password") String password);
}
