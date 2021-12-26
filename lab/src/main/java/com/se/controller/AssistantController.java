package com.se.controller;

import com.se.entity.Administrator;
import com.se.entity.AssistantKey;
import com.se.service.AssistantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "助教控制器")
@RestController
@CrossOrigin
@RequestMapping("Assistant")
public class AssistantController {
    @Autowired
    private AssistantService assistantService;
    @Operation(summary = "增加一个助教")
    @PostMapping("/addAssistant")
    public String add(@RequestBody AssistantKey assistantKey){
        //保存员工信息
//        System.out.println(employee.getEmployeeName());
//        System.out.println(employee.getEmail());
        int res = assistantService.insert(assistantKey);
        //回到员工列表页面，可以使用redirect或者forward
        return Integer.toString(res);
    }
    @Operation(summary = "删除一个助教")
    @DeleteMapping("/deleteAssistant/{key}")
    public String deleteAssistant(@PathVariable("key")AssistantKey key){
        //根据id删除员工
        assistantService.deleteByPrimaryKey(key);
        return key.toString();
    }
}
