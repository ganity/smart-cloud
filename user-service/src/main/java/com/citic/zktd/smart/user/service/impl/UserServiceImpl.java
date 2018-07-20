package com.citic.zktd.smart.user.service.impl;

import com.citic.zktd.smart.RoomApi;
import com.citic.zktd.smart.entry.Room;
import com.citic.zktd.smart.entry.User;
import com.citic.zktd.smart.service.CommonServiceImpl;
import com.citic.zktd.smart.user.repository.UserRepository;
import com.citic.zktd.smart.user.service.UserService;
import com.citic.zktd.smart.util.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/17 11:38
 */
@Service
public class UserServiceImpl extends CommonServiceImpl<UserRepository, User, Long> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoomApi roomApi;

//    @Override
//    public User findOneById(Long id) {
//        return userRepository.getOne(id);
//    }

    @Override
    public User insert(User user) {
        Room room = roomApi.getRoom(1l);
        System.err.println(room.toString());
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return super.insert(user);
    }

//    @Override
//    public User update(User user) {
//        User u = findOneById(user.getId());
//        BeanCopierUtils.copyPropertiesIgnoreNull(user, u);
//        return super.update(u);
//    }

//    @Override
//    public void delete(User user) {
//        userRepository.delete(user);
//    }

//    @Override
//    public Page<User> findByPage(User user, Pageable pageable) {
////        pageable.
//        Example example = Example.of(user);
//
//        return userRepository.findAll(example, pageable);
//    }

//    @Override
//    public List<User> findAll(User user) {
//        Room room = roomApi.getRoom(1l);
//        System.err.println(room.toString());
//        Example example = Example.of(user);
//        return userRepository.findAll(example);
//    }
}
