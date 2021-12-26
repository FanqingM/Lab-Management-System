package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.School;
import com.se.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("School")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/addSchool")
    @Operation(summary = "增加一个院系")
    public String add(@RequestBody School school){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = schoolService.insert(school);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @DeleteMapping("/deleteSchool/{name}")
    @Operation(summary = "删除一个院系")
    public String deleteSchool(@PathVariable("name")String name){
        //根据id删除员工
        schoolService.deleteByPrimaryKey(name);
        return name.toString();
    }
}
