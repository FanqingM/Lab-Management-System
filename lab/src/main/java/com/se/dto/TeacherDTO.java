package com.se.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private String Id;
    private String Name;
    private String schoolName;

    public TeacherDTO(String Id, String Name, String schoolName)
    {
        this.Id = Id;
        this.Name = Name;
        this.schoolName = schoolName;
    }
}
