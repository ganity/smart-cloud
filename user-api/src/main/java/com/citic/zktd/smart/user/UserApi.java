package com.citic.zktd.smart.user;

import com.citic.zktd.smart.entry.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/6/26 15:57
 */
@Component
@FeignClient(value = "user-service", fallback = UserApiHystrix.class)
public interface UserApi {

    @GetMapping("user/{id}")
    User getUser(@PathVariable("id") Long id);

    @GetMapping("user/name/{username}")
    User getUserByName(@PathVariable("username") String username);

    @GetMapping("user/all")
    List<User> getAllUsers();

    @PostMapping("user")
    User insert(User user);
}
