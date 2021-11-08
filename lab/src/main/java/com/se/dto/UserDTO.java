package com.se.dto;


import lombok.Data;

@Data
public class UserDTO {

    String id;
    String password;

    /**
     * 身份，0学生，1管理员，2教师，3责任教师
     */
    Byte authority;

    public UserDTO(String id, String password, Byte authority) {
        this.id = id;
        this.password = password;
        this.authority = authority;
    }
}
