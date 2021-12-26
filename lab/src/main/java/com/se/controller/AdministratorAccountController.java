package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.service.AdministratorAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Tag(name = "管理员账号控制器")
@RestController
@CrossOrigin
@RequestMapping("AdministratorAccount")

public class AdministratorAccountController {
    @Autowired
    private AdministratorAccountService administratorAccountService;
    @GetMapping("findOneAdministratorAccount")

    @Operation(summary = "根据id找到管理员账号")
    public AdministratorAccount findOne(@Parameter(description = "管理员工号") String id) {
        AdministratorAccount administratorAccount = administratorAccountService.selectByPrimaryKey(id);
        return administratorAccount;
    }
    @PostMapping("/addAdministratorAccount")
    @Operation(summary = "添加管理员账号")
    public String add(@RequestBody AdministratorAccount administratorAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = administratorAccountService.insert(administratorAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateAdministratorAccount")
    @Operation(summary = "更新管理员账号")
    public AdministratorAccount updateAdministratorAccount(@RequestBody AdministratorAccount administratorAccount){
        administratorAccountService.updateByPrimaryKey(administratorAccount);
        //回到员工列表页面
        return administratorAccount;
    }

    @DeleteMapping("/deleteAdministratorAccount/{id}")
    @Operation(summary = "删除管理员账号")
    public String deleteAdministratorAccount(@PathVariable("id")String id){
        //根据id删除员工
        administratorAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
