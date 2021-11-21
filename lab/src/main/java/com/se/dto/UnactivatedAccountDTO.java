package com.se.dto;

import lombok.Data;

@Data
public class UnactivatedAccountDTO {
    private String Id;
    private String Name;
    private String isTeacher;
    private String isSeniorTeacher;

    public UnactivatedAccountDTO(String Id,String Name,String isTeacher,String isSeniorTeacher)
    {
        this.Id = Id;
        this.Name = Name;
        this.isTeacher = isTeacher;
        this.isSeniorTeacher = isSeniorTeacher;
    }
}
