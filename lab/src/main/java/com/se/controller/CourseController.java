package com.se.controller;

import com.se.dto.CoursePutINO;
import com.se.entity.AdministratorAccount;
import com.se.entity.Course;
import com.se.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("Course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Operation(summary = "找到一个课程")
    @GetMapping("findOneCourse")
    public Course findOne(String id) {
        Course course = courseService.selectByPrimaryKey(id);
        return course;
    }
    @Operation(summary = "新增一个课程")
    @PostMapping("/addCourse")
    public String add(@RequestBody Course course){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = courseService.insert(course);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }
    @Operation(summary = "更新一个课程")
    @PutMapping("/updateCourse")
    public Course updateCourse(@RequestBody Course course){
        courseService.updateByPrimaryKey(course);
        //回到员工列表页面
        return course;
    }
    @Operation(summary = "更新老师所教课程")
    @PutMapping("/updateTeacher")
    public CoursePutINO updateTeacher(@RequestBody CoursePutINO coursePutINO){
        courseService.updateTeacher(coursePutINO);
        //回到员工列表页面
        return coursePutINO;
    }
//    public int updateTeacher(CoursePutINO coursePutINO)
    @Operation(summary = "删除一个课程")
    @DeleteMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id")String id){
        //根据id删除员工
        courseService.deleteByPrimaryKey(id);
        return id.toString();
    }

}
