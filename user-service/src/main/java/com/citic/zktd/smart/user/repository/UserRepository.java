package com.citic.zktd.smart.user.repository;

import com.citic.zktd.smart.entry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/6 18:05
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
