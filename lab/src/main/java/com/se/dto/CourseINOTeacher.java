package com.se.dto;

import lombok.Data;

@Data
public class CourseINOTeacher {
    private String teacherId;
    private Boolean semeter;
    private String year;

    public CourseINOTeacher(String teacherId, Boolean semeter, String year) {
        this.teacherId = teacherId;
        this.semeter = semeter;
        this.year = year;
    }
}

