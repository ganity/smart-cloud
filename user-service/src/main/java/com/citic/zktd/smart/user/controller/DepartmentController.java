package com.citic.zktd.smart.user.controller;

import com.citic.zktd.smart.entry.Department;
import com.citic.zktd.smart.entry.Organization;
import com.citic.zktd.smart.user.repository.DeptRepository;
import com.citic.zktd.smart.user.repository.OrgRepository;
import com.citic.zktd.smart.user.service.DeptService;
import com.citic.zktd.smart.user.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/10 18:56
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {
//    @Resource
    @Autowired
    private DeptService deptService;
    @Autowired
    private OrgService orgService;
    //    @Resource
    //    private DeptRepository deptRepository;
    //    private OrgRepository orgRepository;


    @GetMapping("/{id}")
    public Department get(@PathVariable Long id){
        Department department = deptService.findOneById(id);

        return department;
    }

    @PostMapping
    public Department post(Department department) {
        return deptService.insert(department);
    }

    @GetMapping("/org/{id}")
    public Organization getOrg(@PathVariable Long id){
        Organization org = orgService.findOneById(id);

        return org;
    }
}
