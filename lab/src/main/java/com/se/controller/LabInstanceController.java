package com.se.controller;

import com.se.dto.LabInstenceDTO;
import com.se.entity.*;
import com.se.service.LabInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("labInstance")
public class LabInstanceController {
    @Autowired
    private LabInstanceService labInstanceService;
    @GetMapping("findOne")
    public LabInstance findOne(LabInstanceKey key) {
        LabInstance labInstance = labInstanceService.selectByPrimaryKey(key);
        return labInstance;
    }

    @PostMapping("/add")
    public String add(@RequestBody LabInstance labInstance){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = labInstanceService.insert(labInstance);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public LabInstance updateLabInstance(@RequestBody LabInstance labInstance){
        labInstanceService.updateByPrimaryKey(labInstance);
        //回到员工列表页面
        return labInstance;
    }

    @DeleteMapping("/delete/{key}")
    public String deleteLabInstance(@PathVariable("key")LabInstanceKey key){
        //根据id删除员工
        labInstanceService.deleteByPrimaryKey(key);
        return key.toString();
    }

    @GetMapping("findOneInfo")
    public LabInstenceDTO findOneInfo(LabInstanceKey labInstanceKey){
        return labInstanceService.findOne(labInstanceKey);
    }

    @GetMapping("findLabInstanceOfSection")
    public List<LabInstenceDTO> findLabInstanceOfSection(SectionKey sectionKey){
        return labInstanceService.findLabInstanceOfSection(sectionKey);
    }

}
