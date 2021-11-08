package com.se.service;

import com.se.util.VerCodeGenerateUtil;
import com.se.util.VerifyCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailServiceImpl implements MailService {


    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendSimpleMail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        String code = VerCodeGenerateUtil.generateVerCode();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("实验系统验证码");
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        mailSender.send(message);
        VerifyCodes.add(to,code);
        return true;
    }
}
