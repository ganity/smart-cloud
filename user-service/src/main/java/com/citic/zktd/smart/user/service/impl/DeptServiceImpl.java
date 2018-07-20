package com.citic.zktd.smart.user.service.impl;

import com.citic.zktd.smart.entry.Department;
import com.citic.zktd.smart.service.CommonServiceImpl;
import com.citic.zktd.smart.user.repository.DeptRepository;
import com.citic.zktd.smart.user.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/20 15:39
 */
@Service
public class DeptServiceImpl extends CommonServiceImpl<DeptRepository, Department, Long> implements DeptService {
}
