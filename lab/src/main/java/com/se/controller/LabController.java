package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.Lab;
import com.se.entity.LabKey;
import com.se.service.LabService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("Lab")
public class LabController {
    @Autowired
    private LabService labService;
    @Operation(summary = "找到一个实验模版")
    @GetMapping("findOneLab")
    public Lab findOne(LabKey key) {
        Lab lab = labService.selectByPrimaryKey(key);
        return lab;
    }
    @Operation(summary = "新增一个实验模版")
    @PostMapping("/addLab")
    public String add(@RequestBody Lab lab){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = labService.insert(lab);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }
    @Operation(summary = "更新一个实验模版")
    @PutMapping("/updateLab")
    public Lab updateLab(@RequestBody Lab lab){
        labService.updateByPrimaryKey(lab);
        //回到员工列表页面
        return lab;
    }

    @Operation(summary = "删除一个实验模版")
    @DeleteMapping("/deleteLab/{key}")
    public String deleteLab(@PathVariable("key")LabKey key){
        //根据id删除员工
        labService.deleteByPrimaryKey(key);
        return key.toString();
    }
}
