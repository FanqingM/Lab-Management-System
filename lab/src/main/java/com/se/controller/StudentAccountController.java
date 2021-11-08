package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.StudentAccount;
import com.se.intercepter.JwtToken;
import com.se.service.StudentAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("studentAccount")
public class StudentAccountController {
    @Autowired
    private StudentAccountService studentAccountService;

    @JwtToken
    @SecurityRequirement(name = "token")
    @GetMapping("findOne")
    public StudentAccount findOne(String id) {
        StudentAccount studentAccount = studentAccountService.selectByPrimaryKey(id);
        return studentAccount;
    }

    @PostMapping("/add")
    public String add(@RequestBody StudentAccount studentAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = studentAccountService.insert(studentAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public StudentAccount updateStudentAccount(@RequestBody StudentAccount studentAccount){
        studentAccountService.updateByPrimaryKey(studentAccount);
        //回到员工列表页面
        return studentAccount;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentAccount(@PathVariable("id")String id){
        //根据id删除员工
        studentAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
