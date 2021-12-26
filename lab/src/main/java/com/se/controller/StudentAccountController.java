package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.StudentAccount;
import com.se.intercepter.JwtToken;
import com.se.service.StudentAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("StudentAccount")
public class StudentAccountController {
    @Autowired
    private StudentAccountService studentAccountService;

    @JwtToken(authority = "2")
    @SecurityRequirement(name = "token")
    @GetMapping("findOneStudentAccount")
    @Operation(summary = "找到一个学生账户")
    public StudentAccount findOne(String id) {
        StudentAccount studentAccount = studentAccountService.selectByPrimaryKey(id);
        return studentAccount;
    }

    @PostMapping("/addStudentAccount")
    @Operation(summary = "增加一个学生账户")
    public String add(@RequestBody StudentAccount studentAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = studentAccountService.insert(studentAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateStudentAccount")
    @Operation(summary = "更新一个学生账户")
    public StudentAccount updateStudentAccount(@RequestBody StudentAccount studentAccount){
        studentAccountService.updateByPrimaryKey(studentAccount);
        //回到员工列表页面
        return studentAccount;
    }

    @DeleteMapping("/deleteStudentAccount/{id}")
    @Operation(summary = "删除一个学生账户")
    public String deleteStudentAccount(@PathVariable("id")String id){
        //根据id删除员工
        studentAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
