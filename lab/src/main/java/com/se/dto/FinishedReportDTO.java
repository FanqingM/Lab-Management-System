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

    public FinishedReportDTO(String labId,String labName, Integer grades, String courseName, Date endTime)
    {
        this.grades = grades;
        this.labId = labId;
        this.labName = labName;
        this.courseName = courseName;
        this.endTime = endTime;
    }
}
