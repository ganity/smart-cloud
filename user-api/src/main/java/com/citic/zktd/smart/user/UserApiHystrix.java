package com.citic.zktd.smart.user;

import com.citic.zktd.smart.entry.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 594781919@qq.com
 * @date: 2018/5/8
 */
@Component
public class UserApiHystrix implements UserApi {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public User getUser(Long id) {
        logger.info("执行hystrix中的getUser方法");
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("执行hystrix中的getAllUsers方法");
        return null;
    }

    @Override
    public User insert(User user) {
        return user;
    }
}
