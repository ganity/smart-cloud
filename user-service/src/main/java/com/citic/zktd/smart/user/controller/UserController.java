package com.citic.zktd.smart.user.controller;

import com.citic.zktd.smart.entry.User;
import com.citic.zktd.smart.user.UserApi;
import com.citic.zktd.smart.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/6/26 16:04
 */
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController implements UserApi {

//    @Resource
//    private SimpleUserRepository userRepository;
//    @Autowired
//    private PasswordService passwordService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @Override
    public User getUser(@PathVariable Long id) {
//        User user = new User();
//        user.setId(id);
//        user.setUserName("zhangsan");
//        user.setNickName("三张");
//        user.setEmail("aa.@aa.com");
//        Optional<User> userOptional = userRepository.findById(id);
//        return userOptional.get();
        return userService.findOneById(id);
    }

    @Override
    @GetMapping("/name/{username}")
    public User getUserByName(@PathVariable String username) {
        User ex = new User();
        ex.setUserName(username);
        Example example = Example.of(ex);
//        Optional<User> userOptional = userRepository.findOne(example);
        System.err.println("username:"+username);
//        return userOptional.get();
//        Result<User> result = new Result<>();
//        result.failed(Result.ResultCode.AUTHENTICATED_DENIED_ERROR_TIMESTAMP_TIMEOUT);
//        Result<User> result = Result.ofSuccess(ex);
        return ex;
    }


    @Override
    @GetMapping("/all")
    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
        return userService.findAll(new User());
    }

    @PostMapping
    @Override
    public User insert(User user) {
//        String password = passwordService.getPassword(user.getUserPassword());
//        System.err.println(password);
//        user.setUserPassword(password);
//        return userRepository.save(user);
        return userService.insert(user);
    }

    @GetMapping("/page")
    public Page<User> getAllPage(User user, @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                         Pageable pageable) {
//        Page<User> users = userRepository.findAll(pageable);
        System.err.println(user.toString());
        Page<User> users = userService.findByPage(user, pageable);
        return users;
    }
}
