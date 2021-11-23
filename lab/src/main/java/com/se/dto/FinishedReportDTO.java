package com.se.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.sql.Date;

@Data
public class FinishedReportDTO {
    private String labId;
    private String labName;
//    private Date endTime;
    private Integer grades;
    private String courseName;
    private Date endTime;
    private String courseId;
    private String sectionId;

    public FinishedReportDTO(String labId,String labName, Integer grades, String courseName, Date endTime, String courseId, String sectionId)
    {
        this.grades = grades;
        this.labId = labId;
        this.labName = labName;
        this.courseName = courseName;
        this.endTime = endTime;
        this.courseId = courseId;
        this.sectionId = sectionId;
    }
}
