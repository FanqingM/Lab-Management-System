package com.se.controller;

import com.se.entity.Administrator;
import com.se.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("Administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

//    @GetMapping("finadAll")
//    public Collection<Administrator> findAll() {
//        Collection<Administrator> administrators = administratorService.selectAllAdministrator();
//        return administrators;
//    }

    @GetMapping("findOneAdministrator")
    public Administrator findOne(String id) {
        Administrator administrator = administratorService.selectByPrimaryKey(id);
        return administrator;
    }

    @PostMapping("/addAdministrator")
    public String add(@RequestBody Administrator administrator){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = administratorService.insert(administrator);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/updateAdministrator")
    public Administrator updateAdministrator(@RequestBody Administrator administrator){
        administratorService.updateByPrimaryKey(administrator);
        //回到员工列表页面
        return administrator;
    }

    @DeleteMapping("/deleteAdministrator/{id}")
    public String deleteAdministrator(@PathVariable("id")String id){
        //根据id删除员工
        administratorService.deleteByPrimaryKey(id);
        return id.toString();
    }


//    private static Map<String,String> code = new HashMap<>();


}
