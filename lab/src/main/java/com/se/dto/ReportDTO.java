package com.se.dto;

import com.se.entity.ReportKey;
import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class ReportDTO extends ReportKey {
    Integer grades;
    String name;

    public ReportDTO(String studentId, String courseId, String sectionId, String labId, Integer grades, String name) {
        super(studentId, courseId, sectionId, labId);
        this.grades = grades;
        this.name = name;
    }
}
