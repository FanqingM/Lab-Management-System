package com.se.controller;

import com.se.dto.LabInstenceDTO;
import com.se.entity.*;
import com.se.service.LabInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("LabInstance")
public class LabInstanceController {
    @Autowired
    private LabInstanceService labInstanceService;
    @GetMapping("findOneLabInstance")
    @Operation(summary = "找到一个实验")
    public LabInstance findOne(LabInstanceKey key) {
        LabInstance labInstance = labInstanceService.selectByPrimaryKey(key);
        return labInstance;
    }

    @PostMapping("/addLabInstance")
    @Operation(summary = "增加一个实验")
    public String add(@RequestBody LabInstance labInstance){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = labInstanceService.insert(labInstance);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateLabInstance")
    @Operation(summary = "更新一个实验")
    public LabInstance updateLabInstance(@RequestBody LabInstance labInstance){
        labInstanceService.updateByPrimaryKey(labInstance);
        //回到员工列表页面
        return labInstance;
    }

    @DeleteMapping("/deleteLabInstance/{key}")
    @Operation(summary = "删除一个实验")
    public String deleteLabInstance(@PathVariable("key")LabInstanceKey key){
        //根据id删除员工
        labInstanceService.deleteByPrimaryKey(key);
        return key.toString();
    }

    @GetMapping("findOneLabInstanceInfo")
    @Operation(summary = "找到一个实验所有信息")
    public LabInstenceDTO findOneInfo(LabInstanceKey labInstanceKey){
        return labInstanceService.findOne(labInstanceKey);
    }

    @GetMapping("findLabInstanceOfSection")
    @Operation(summary = "找到一个时间段的所有实验")
    public List<LabInstenceDTO> findLabInstanceOfSection(SectionKey sectionKey){
        return labInstanceService.findLabInstanceOfSection(sectionKey);
    }

}
