package com.se.controller;

import com.se.entity.Administrator;
import com.se.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController

@RequestMapping("administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("finadAll")
    public Collection<Administrator> findAll() {
        Collection<Administrator> administrators = administratorService.selectAllAdministrator();
        return administrators;
    }

    @GetMapping("findOne")
    public Administrator findOne(String id) {
        Administrator administrator = administratorService.selectByPrimaryKey(id);
        return administrator;
    }

    @PostMapping("/add")
    public String add(@RequestBody Administrator administrator){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        administratorService.insert(administrator);
        //回到员工列表页面，可以使用redirect或者forward
        return "test";
    }

//    //to员工修改页面
//    @GetMapping("/emp/{id}")
//    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
//        //根据id查出来员工
//        Employee employee = employeeService.selectEmployeeById(id);
//        //将员工信息返回页面
//        model.addAttribute("emp",employee);
//        //查出所有的部门，提供修改选择
//        Collection<Department> departments = departmentService.selectAllDepartment();
//        model.addAttribute("departments",departments);
//
//        return "test";
//    }

    @PutMapping("/update")
    public Administrator updateAdministrator(@RequestBody Administrator administrator){
        administratorService.updateByPrimaryKey(administrator);
        //回到员工列表页面
        return administrator;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAdministrator(@PathVariable("id")String id){
        //根据id删除员工
        administratorService.deleteByPrimaryKey(id);
        return id.toString();
    }


}
