package com.se.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Files {
    private String courseId;
    private String url;
    private Date time;
    private String fileName;

    public Files(String courseId, String url, Date time, String fileName) {
        this.courseId = courseId;
        this.url = url;
        this.time = time;
        this.fileName = fileName;
    }

}
