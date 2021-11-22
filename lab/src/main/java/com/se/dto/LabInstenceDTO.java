package com.se.dto;

import com.se.entity.LabInstance;
import lombok.Data;

import java.util.Date;

@Data
public class LabInstenceDTO extends LabInstance {

    private String name;

    public LabInstenceDTO(String courseId, String sectionId, String labId, Date startTime, Date endTime, Integer submitNum, String name) {
        super(courseId, sectionId, labId, startTime, endTime, submitNum);
        this.name = name;
    }
}
