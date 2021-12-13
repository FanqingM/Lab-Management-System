package com.se.controller;

import com.se.dto.*;
import com.se.entity.AdministratorAccount;
import com.se.entity.Teacher;
import com.se.entity.TeacherAccount;
import com.se.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;


    @GetMapping("findAll")
    public List<TeacherDTO> selectAllTeachers() {
        List<TeacherDTO> res = teacherService.selectAllTeachers();
        return res;
    }
    @GetMapping("findAllCourses")
    public List<CourseDTO> selectAllCourses(CourseINOTeacher courseINOTeacher) {
        List<CourseDTO> res = teacherService.selectAllCourses(courseINOTeacher);
        return res;
    }

    @GetMapping("findOne")
    public Teacher findOne(String id) {
        Teacher teacher = teacherService.selectByPrimaryKey(id);
        return teacher;
    }

    @PostMapping("/add")
    public String add(@RequestBody TeacherINO teacherINO){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = teacherService.insert(teacherINO);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Teacher updateTeacher(@RequestBody Teacher teacher){
        teacherService.updateByPrimaryKey(teacher);
        //回到员工列表页面
        return teacher;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id")String id){
        //根据id删除员工
        teacherService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
