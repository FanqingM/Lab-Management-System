package com.se.controller;

import com.se.service.MailService;
import com.se.util.VerifyCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //数据会以json形式返回
@RequestMapping("VerifyCode")
public class VerifyEmailController {

    @Autowired
    MailService mailService;

    @PostMapping("/getCode")
    public Boolean addActivity(@RequestParam String mail){ //传过来的json包装成实体
        return mailService.sendSimpleMail(mail);
    }

    @PostMapping("/verify")
    public Boolean verify(@RequestParam("email") String email, @RequestParam("code") String code){
        return VerifyCodes.verify(email,code);
    }
}
