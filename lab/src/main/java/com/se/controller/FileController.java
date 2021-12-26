package com.se.controller;

import com.se.entity.Files;
import com.se.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("File")
public class FileController {
    @Autowired
    private FileService fileService;
    @Operation(summary = "找到所有文件")
    @GetMapping("findAllFile")
    public List<Files> FindAll()
    {
        return fileService.selectAllFile();
    }

    @Operation(summary = "找到一个课程的所有文件")
    @GetMapping("findAllFileByCourseId")
    public List<Files> FindAllByCourseId(String courseId)
    {
        return fileService.selectByCourseId(courseId);
    }

    @Operation(summary = "增加文件")
    @PostMapping("addFile")
    public int AddFile(@RequestBody Files files)
    {
        Date date = new Date();
        files.setTime(date);
        return fileService.insert(files);
    }
}
