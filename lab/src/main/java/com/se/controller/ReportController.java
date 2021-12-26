package com.se.controller;

import com.se.dto.FinishedReportDTO;
import com.se.dto.ReportDTO;
import com.se.entity.*;
import com.se.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("Report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("findOneReport")
    public Report findOne(ReportKey key) {
        Report report = reportService.selectByPrimaryKey(key);
        return report;
    }

    @GetMapping("findReport")
    public List<Report> findReport(String studentId) {
        List<Report> listReports = reportService.selectReport(studentId);
        return listReports;
    }
    @GetMapping("findFinishedReports")
    public List<FinishedReportDTO> findFinishedReports(String studentId) {
        List<FinishedReportDTO> reports = reportService.selectFinishedReports(studentId);
        return reports;
    }
    @PostMapping("/addReport")
    public String add(@RequestBody Report report){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = reportService.insert(report);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }
    @CrossOrigin
    @PutMapping("/updateReport")
    public Report updateReport(@RequestBody Report report){
        reportService.updateByPrimaryKey(report);
        //回到员工列表页面
        return report;
    }

    @DeleteMapping("/deleteReport/{key}")
    public String deleteReport(@PathVariable("key")ReportKey key){
        //根据id删除员工
        reportService.deleteByPrimaryKey(key);
        return key.toString();
    }

    @GetMapping("findReportsOfLabInstance")
    public List<ReportDTO> findReportsOfLabInstance(LabInstanceKey labInstanceKey){
        return reportService.findReportsOfLabInstance(labInstanceKey);
    }

}
