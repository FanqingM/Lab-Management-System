package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.Course;
import com.se.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping("findOne")
    public Course findOne(String id) {
        Course course = courseService.selectByPrimaryKey(id);
        return course;
    }

    @PostMapping("/add")
    public String add(@RequestBody Course course){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = courseService.insert(course);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course course){
        courseService.updateByPrimaryKey(course);
        //回到员工列表页面
        return course;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id")String id){
        //根据id删除员工
        courseService.deleteByPrimaryKey(id);
        return id.toString();
    }

}
