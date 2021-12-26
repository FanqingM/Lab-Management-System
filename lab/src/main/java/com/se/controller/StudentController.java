package com.se.controller;

import com.se.dto.CourseDTO;
import com.se.dto.CourseINO;
import com.se.dto.StudentDTO;
import com.se.entity.AdministratorAccount;
import com.se.entity.Student;
import com.se.service.SectionService;
import com.se.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("findAllStudent")
    public List<StudentDTO> selectAllStudents() {
        List<StudentDTO> res = studentService.selectAllStudents();
        return res;
    }
    @GetMapping("findAllCourses")
    public List<CourseDTO> selectAllCourses(CourseINO courseINO) {
        List<CourseDTO> res = studentService.selectAllCourses(courseINO);
        return res;
    }
    @GetMapping("findOneStudent")
    public Student findOne(String id) {
        Student student = studentService.selectByPrimaryKey(id);
        return student;
    }

    @GetMapping("downloadFile")
    public ResponseEntity<InputStreamSource> download(String filename) {
        // 构建下载路径
        String path = "/root/lab/se";
        File target = new File(path + "//" + filename);
        // 构建响应体
        if (target.exists()) {
            FileSystemResource resource = new FileSystemResource(target);
            return ResponseEntity.ok()
                    // 指定文件的contentType
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            // 如果文件不存在，返回404响应
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addStudent")
    public String add(@RequestBody Student student){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = studentService.insert(student);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PostMapping("/addManyStudent")
    public int insertManyStudents(@RequestBody List<Student> students) {
        int res = studentService.insertManyStudents(students);
        return res;
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student){
        studentService.updateByPrimaryKey(student);
        //回到员工列表页面
        return student;
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id")String id){
        //根据id删除员工
        studentService.deleteByPrimaryKey(id);
        return id.toString();
    }
}
