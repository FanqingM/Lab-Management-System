package com.se.controller;

import com.se.entity.AdministratorAccount;
import com.se.entity.Takes;
import com.se.entity.TakesKey;
import com.se.service.TakesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("Takes")
public class TakesController {
    @Autowired
    private TakesService takesService;

    @GetMapping("findOneTakes")
    public Takes findOne(TakesKey key) {
        Takes takes = takesService.selectByPrimaryKey(key);
        return takes;
    }

    @PostMapping("/addTakes")
    public String add(@RequestBody Takes takes){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = takesService.insert(takes);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateTakes")
    public Takes updateTakes(@RequestBody Takes takes){
        takesService.updateByPrimaryKey(takes);
        //回到员工列表页面
        return takes;
    }

    @DeleteMapping("/deleteTakes/{key}")
    public String deleteTakes(@PathVariable("key")TakesKey key){
        //根据id删除员工
        takesService.deleteByPrimaryKey(key);
        return key.toString();
    }
}
