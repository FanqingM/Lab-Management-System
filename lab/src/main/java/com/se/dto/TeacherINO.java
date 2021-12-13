package com.se.dto;

import lombok.Data;

@Data
public class TeacherINO {
    private String id;
    private String name;
    private String schoolName;
    private String email;

    public TeacherINO(String id, String name, String schoolName, String email)
    {
        this.id = id;
        this.name = name;
        this.schoolName = schoolName;
        this.email = email;
    }
}
