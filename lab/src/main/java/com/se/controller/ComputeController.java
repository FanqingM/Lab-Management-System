package com.se.controller;

import com.se.entity.Course;
import com.se.service.ComputeService;
import com.se.service.CourseService;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("compute")
public class ComputeController {
    @Autowired
    private ComputeService computeService;

    @CrossOrigin
    @GetMapping("compute")
    public ArrayList<ArrayList<Double>> ComputeRes ( @RequestParam("list") ArrayList<Double> list) {
        return computeService.Compute(list);
    }
}
