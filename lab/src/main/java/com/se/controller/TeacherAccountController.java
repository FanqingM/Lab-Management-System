package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.TeacherAccount;
import com.se.service.TeacherAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("teacherAccount")
public class TeacherAccountController {
    @Autowired
    private TeacherAccountService teacherAccountService;

    @GetMapping("findOne")
    public TeacherAccount findOne(String id) {
        TeacherAccount teacherAccount = teacherAccountService.selectByPrimaryKey(id);
        return teacherAccount;
    }

    @PostMapping("/add")
    public String add(@RequestBody TeacherAccount teacherAccount){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = teacherAccountService.insert(teacherAccount);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public TeacherAccount updateTeacherAccount(@RequestBody TeacherAccount teacherAccount){
        teacherAccountService.updateByPrimaryKey(teacherAccount);
        //回到员工列表页面
        return teacherAccount;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacherAccount(@PathVariable("id")String id){
        //根据id删除员工
        teacherAccountService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
