package com.se.controller;

import com.se.dto.*;
import com.se.entity.AdministratorAccount;
import com.se.entity.Files;
import com.se.entity.Teacher;
import com.se.entity.TeacherAccount;
import com.se.mapper.FileMapper;
import com.se.service.FileService;
import com.se.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private FileService fileService;


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

    @PostMapping("/addMany")
    public String addManyTeachers(@RequestBody List<TeacherINO> teacherINOs){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = teacherService.insertManyTeachers(teacherINOs);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PostMapping("/addFile")
    public String addFile(@RequestParam("file") MultipartFile file, String courseId)
            throws IllegalStateException, IOException {


        System.out.println(courseId);  //正确赋值到了参数上

//        String path = "/Users/fanqing_m/desktop/se";  //本地测试
        String path = "/root/lab/se";
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
//        int res = teacherService.insertManyTeachers(teacherINOs);
//        //回到员工列表页面，可以使用redirect或者forward
        // 获取原始文件名
        String filename = file.getOriginalFilename();
        String url = path + "//" + filename;
        Date date = new Date();
        Files f = new Files(courseId,url,date,filename);
        fileService.insert(f);
//        String filename = file.getName();
        // 构建保存目标
        File target = new File(path + "//" + filename);
        // 将文件转移到指定目录
        file.transferTo(target);
        // 构建响应
        Map<String, Object> response = new HashMap<>();
        response.put("target", target.getAbsolutePath());
        return "good";
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
