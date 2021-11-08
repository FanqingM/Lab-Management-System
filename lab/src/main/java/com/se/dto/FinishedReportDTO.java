package com.se.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.sql.Date;

@Data
public class FinishedReportDTO {
    private String labId;
    private String labName;
    private Date endTime;
    private Integer grades;

    public FinishedReportDTO(String labId,String labName,Date endTime, Integer grades)
    {
        this.grades = grades;
        this.labId = labId;
        this.labName = labName;
        this.endTime = endTime;
    }
}
