package com.se.controller;

import com.se.dto.SectionDTO;
import com.se.entity.Lab;
import com.se.entity.LabKey;
import com.se.entity.Section;
import com.se.entity.SectionKey;
import com.se.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("findOne")
    public Section findOne(SectionKey key) {
        Section section = sectionService.selectByPrimaryKey(key);
        return section;
    }

    @PostMapping("/add")
    public String add(@RequestBody Section section) {
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = sectionService.insert(section);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Section updateSection(@RequestBody Section section) {
        sectionService.updateByPrimaryKey(section);
        //回到员工列表页面
        return section;
    }

    @DeleteMapping("/delete/{key}")
    public String deleteSection(@PathVariable("key") SectionKey key) {
        //根据id删除员工
        sectionService.deleteByPrimaryKey(key);
        return key.toString();
    }

    @GetMapping("teacherSection")
    public List<SectionDTO> findSectionsOfTeacher(String teacherId) {
        return sectionService.findSectionsOfTeacher(teacherId);
    }

    @GetMapping("findOneSection")
    public SectionDTO findOneSection(String courseId, String section_Id) {
        return sectionService.findOneSection(courseId, section_Id);
    }
}