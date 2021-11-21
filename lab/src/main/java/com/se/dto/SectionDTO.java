package com.se.dto;

import com.se.entity.Section;
import lombok.Data;

@Data
public class SectionDTO extends Section {

    private String courseName;
    private Integer credits;
    private Boolean hasNotGrading;

    public SectionDTO(String courseId, String sectionId, String tercherId, Boolean semeter, String year, String courseName, Integer credits) {
        super(courseId, sectionId, tercherId, semeter, year);
        this.courseName = courseName;
        this.credits = credits;
    }
}
