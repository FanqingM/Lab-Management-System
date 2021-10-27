package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.Student;
import com.se.service.SectionService;
import com.se.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("findOne")
    public Student findOne(String id) {
        Student student = studentService.selectByPrimaryKey(id);
        return student;
    }

    @PostMapping("/add")
    public String add(@RequestBody Student student){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = studentService.insert(student);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student){
        studentService.updateByPrimaryKey(student);
        //回到员工列表页面
        return student;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id")String id){
        //根据id删除员工
        studentService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
