package com.se.controller;

import com.se.entity.Administrator;
import com.se.entity.AdministratorAccount;
import com.se.mapper.AdministratorAccountMapper;
import com.se.service.AdministratorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("AdministratorAccount")
public class AdministratorAccountController {
    @Autowired
    private AdministratorAccountService administratorAccountService;

    @GetMapping("findOneAdministratorAccount")
    public AdministratorAccount findOne(String id) {
        AdministratorAccount administratorAccount = administratorAccountService.selectByPrimaryKey(id);
        return administratorAccount;
    }

    @PostMapping("/addAdministratorAccount")
    public String add(@RequestBody AdministratorAccount administratorAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = administratorAccountService.insert(administratorAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateAdministratorAccount")
    public AdministratorAccount updateAdministratorAccount(@RequestBody AdministratorAccount administratorAccount){
        administratorAccountService.updateByPrimaryKey(administratorAccount);
        //回到员工列表页面
        return administratorAccount;
    }

    @DeleteMapping("/deleteAdministratorAccount/{id}")
    public String deleteAdministratorAccount(@PathVariable("id")String id){
        //根据id删除员工
        administratorAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
