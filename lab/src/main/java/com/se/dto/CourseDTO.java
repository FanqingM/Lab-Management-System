package com.se.dto;

public class CourseDTO {
    private String courseId;
    private String sectionId;
    private Boolean semeter;
    private String year;
    private String courseName;

    public CourseDTO(String courseId, String sectionId,Boolean semeter, String year, String courseName) {
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.semeter = semeter;
        this.year = year;
        this.courseName = courseName;
    }
}
