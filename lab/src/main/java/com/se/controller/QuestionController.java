package com.se.controller;


import com.se.entity.Lab;
import com.se.entity.LabKey;
import com.se.entity.Question;
import com.se.mapper.QuestionMapper;
import com.se.service.ExerciseService;
import com.se.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("question")
public class QuestionController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("test")
    public List<Question> findNQuestions(){
        return exerciseService.findNQuestions(3);
    }
}
