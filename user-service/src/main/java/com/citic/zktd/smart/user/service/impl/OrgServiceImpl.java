package com.citic.zktd.smart.user.service.impl;

import com.citic.zktd.smart.entry.Organization;
import com.citic.zktd.smart.service.CommonServiceImpl;
import com.citic.zktd.smart.user.repository.OrgRepository;
import com.citic.zktd.smart.user.service.OrgService;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/20 15:39
 */
@Service
public class OrgServiceImpl extends CommonServiceImpl<OrgRepository, Organization, Long> implements OrgService {
}
