package com.se.dto;

import lombok.Data;

@Data
public class CourseINO {
    private String studentId;
    private Boolean semeter;
    private String year;

    public CourseINO(String studentId, Boolean semeter, String year) {
        this.studentId = studentId;
        this.semeter = semeter;
        this.year = year;
    }
}

