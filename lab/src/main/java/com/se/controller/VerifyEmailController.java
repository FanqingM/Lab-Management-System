package com.se.controller;

import com.se.service.MailService;
import com.se.util.VerifyCodes;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //数据会以json形式返回
@RequestMapping("VerifyCode")
public class VerifyEmailController {

    @Autowired
    MailService mailService;

    @PostMapping("/getCode")
    public Boolean addActivity(@RequestBody email mail){ //传过来的json包装成实体
        return mailService.sendSimpleMail(mail.getEmail());
    }

    @PostMapping("/verify")
    public Boolean verify(@RequestBody verifyCode verifyCode){
        return VerifyCodes.verify(verifyCode.getEmail(),verifyCode.getCode());
    }
}

@Data
class email{
    String email;
    public email(String email) {
        this.email = email;
    }
    public email() {
    }
}

@Data
class verifyCode{
    String email;
    String code;
    public verifyCode(String email,String code) {
        this.email = email;
        this.code = code;
    }
    public verifyCode() {
    }
}