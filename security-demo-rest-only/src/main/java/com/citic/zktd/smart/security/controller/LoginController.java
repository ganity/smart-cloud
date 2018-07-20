package com.citic.zktd.smart.security.controller;

import com.citic.zktd.smart.user.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by wangyunfei on 2017/6/12.
 */
@Controller
public class LoginController {

    @Autowired
    private UserApi userService;

    @GetMapping("/login")
    public String user(Authentication user){
        if (null != user && user.isAuthenticated())
            return "index";
        return "login";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
//        return "login";
//    }
}
