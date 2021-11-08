package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.Lab;
import com.se.entity.LabKey;
import com.se.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("lab")
public class LabController {
    @Autowired
    private LabService labService;
    @GetMapping("findOne")
    public Lab findOne(LabKey key) {
        Lab lab = labService.selectByPrimaryKey(key);
        return lab;
    }

    @PostMapping("/add")
    public String add(@RequestBody Lab lab){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = labService.insert(lab);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Lab updateLab(@RequestBody Lab lab){
        labService.updateByPrimaryKey(lab);
        //回到员工列表页面
        return lab;
    }

    @DeleteMapping("/delete/{key}")
    public String deleteLab(@PathVariable("key")LabKey key){
        //根据id删除员工
        labService.deleteByPrimaryKey(key);
        return key.toString();
    }
}
