package com.se.controller;

import com.se.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //数据会以json形式返回
@RequestMapping("VerifyCode")
public class VerifyEmailController {

    @Autowired
    MailService mailService;

    @PostMapping
    public Boolean addActivity(@RequestBody String mail){ //传过来的json包装成实体
        return mailService.sendSimpleMail(mail);
    }


}
