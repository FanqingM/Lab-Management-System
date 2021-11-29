package com.se.controller;

import com.se.entity.Course;
import com.se.service.ComputeService;
import com.se.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("compute")
public class ComputeController {
    @Autowired
    private ComputeService computeService;
    @GetMapping("compute")
    public ArrayList<ArrayList<Double>> ComputeRes (ArrayList<Double> list) {
        return computeService.Compute(list);
    }
}
