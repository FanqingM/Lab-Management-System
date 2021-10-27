package com.se.controller;

import com.se.entity.Employee;
import com.se.service.EmployeeService;
import com.se.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController

@RequestMapping("emploee")
public class EmploeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    //查询所有员工，返回列表页面
    @GetMapping("/findAll")
    public Collection<Employee> list(){
        Collection<Employee> employees = employeeService.selectAllEmployee();
//        将结果放在请求中
//        model.addAttribute("emps",employees);
        return employees;
    }
    @GetMapping("findOne")
    public Employee findOne(int id) {
        Employee employee = employeeService.selectEmployeeById(id);
        return employee;
    }
//    //to员工添加页面
//    @GetMapping("/add")
//    public String toAdd(Model model){
//        //查出所有的部门，提供选择
//        Collection<Department> departments = departmentService.selectAllDepartment();
//        model.addAttribute("departments",departments);
//        return "test";
//    }

    //员工添加功能，使用post接收
    //@requestBody一定加，不然他不知道这个是在body里的，可能认为是参数，post的参数
    @PostMapping("/add")
    public String add(@RequestBody Employee employee){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        employeeService.addEmployee(employee);
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

    @PutMapping("/updateEmp")
    public Employee updateEmp(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        //回到员工列表页面
        return employee;
    }

    @DeleteMapping("/delEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        //根据id删除员工
        employeeService.deleteEmployee(id);
        return id.toString();
    }


}
