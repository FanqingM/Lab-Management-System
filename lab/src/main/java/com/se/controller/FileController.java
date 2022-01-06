package com.se.controller;

import com.se.entity.Files;
import com.se.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;
    @GetMapping("findAll")
    public List<Files> FindAll()
    {
        return fileService.selectAllFile();
    }

    @GetMapping("findAllByCourseId")
    public List<Files> FindAllByCourseId(String courseId)
    {
        return fileService.selectByCourseId(courseId);
    }

    @PostMapping("addFile")
    public int AddFile(@RequestBody Files files)
    {
        Date date = new Date();
        files.setTime(date);
        return fileService.insert(files);
    }
}
