package com.citic.zktd.smart.security.controller;

import com.citic.zktd.smart.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/6 18:35
 */
@RestController
@RequestMapping("/password")
public class PasswordServerImpl implements PasswordService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @GetMapping("/encode")
    public String getPassword(@RequestParam("password") String password) {

        return passwordEncoder.encode(password);
    }
}
