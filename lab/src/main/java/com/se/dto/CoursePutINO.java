package com.se.dto;

import lombok.Data;

@Data
public class CoursePutINO {
    private String courseId;
    private String teacherId;

    public CoursePutINO(String courseId, String teacherId)
    {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }
}
