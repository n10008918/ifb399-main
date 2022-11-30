package com.example.service.Imp;

import cn.hutool.core.util.RandomUtil;
import com.example.domain.Mail;
import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImp implements MailService {
    private Integer code;

    @Value("${spring.mail.username}")
    private String senderName = "";
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public boolean sendMessage(Mail mail) {
        try {
            code = RandomUtil.randomInt(100000, 999999);
            mail.setCode(code);
            mail.setSenderName(senderName);
            // 用来设置邮件信息
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            // 设置邮件标题
            simpleMailMessage.setSubject("login verification code");
            // 设置邮件内容
            simpleMailMessage.setText("your verification code is：" + mail.getCode());
            // 设置源邮箱
            simpleMailMessage.setFrom(mail.getSenderName());
            // 设置目的邮箱
            simpleMailMessage.setTo(mail.getSendedName());
            // 发送
            javaMailSender.send(simpleMailMessage);
            // 没有出现异常，正常发送，返回true
            return true;
        } catch (MailException e) {
            // 发送过程中，发生错误，打印错误信息，返回false
            e.printStackTrace();
            return false;
        }
    }
}
