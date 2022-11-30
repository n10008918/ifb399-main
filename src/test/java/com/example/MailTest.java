package com.example;

import com.example.domain.Mail;
import com.example.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class MailTest {
    @Autowired
    MailService mailService;

//    @Autowired
//    JavaMailSender javaMailSender;

//    public boolean sendEmailCode(String code, String from, String to) {
//        try {
//            // 用来设置邮件信息
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            // 设置邮件标题
//            simpleMailMessage.setSubject("登录验证码");
//            // 设置邮件内容
//            simpleMailMessage.setText("您收到的验证码是：" + code);
//            // 设置源邮箱
//            simpleMailMessage.setFrom(from);
//            // 设置目的邮箱
//            simpleMailMessage.setTo(to);
//            // 发送
//            javaMailSender.send(simpleMailMessage);
//            // 没有出现异常，正常发送，返回true
//            return true;
//        } catch (MailException e) {
//            // 发送过程中，发生错误，打印错误信息，返回false
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Test
    public void test1() {
//        Mail mail = new Mail(123,"3413813083@qq.com", "2754496408@qq.com");
        Mail mail = new Mail();
        mail.setSendedName("2754496408@qq.com");
        mailService.sendMessage(mail);
//        // 设置验证码，可以自己随机生成
//        String code = "011635";
//        boolean b = sendEmailCode(code, "3413813083@qq.com", "3071265458@qq.com");
    }

}
