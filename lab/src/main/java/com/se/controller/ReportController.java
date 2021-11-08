package com.se.controller;

import com.se.entity.Administrator;
import com.se.entity.Report;
import com.se.entity.ReportKey;
import com.se.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("findOne")
    public Report findOne(ReportKey key) {
        Report report = reportService.selectByPrimaryKey(key);
        return report;
    }

    @PostMapping("/add")
    public String add(@RequestBody Report report){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = reportService.insert(report);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }

    @PutMapping("/update")
    public Report updateReport(@RequestBody Report report){
        reportService.updateByPrimaryKey(report);
        //回到员工列表页面
        return report;
    }

    @DeleteMapping("/delete/{key}")
    public String deleteReport(@PathVariable("key")ReportKey key){
        //根据id删除员工
        reportService.deleteByPrimaryKey(key);
        return key.toString();
    }
}
