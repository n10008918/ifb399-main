package com.example.controller;

import com.example.domain.Mail;
import com.example.service.Imp.MailServiceImp;
import com.example.service.MailService;
import com.example.util.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping("/sendCode")
    @ResponseBody
    public R<String> sendCode(@RequestBody Mail mail, HttpSession httpSession){
        System.out.println("mail value" + mail);
        mailService.sendMessage(mail);//set mail code
        httpSession.setAttribute("MailCode",mail.getCode());
        System.out.println("MailCodeSession " + httpSession.getAttribute("MailCode"));
        return R.success("send success");
    }

    @RequestMapping("checkCode")
    @ResponseBody
    public R<String> checkCode(String code, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return R.error("verification code has not been sent");
        }
        if(session.getAttribute("MailCode") != null){//email is valid and code has sent
            String mailCode = String.valueOf(session.getAttribute("MailCode"));
            if(code.equals(mailCode)){
                session.removeAttribute("MailCode");
                return R.success("verification code is valid");
            }else{
                return R.error("verification code is invalid");
            }
        }


        return R.error("verification code is invalid");

    }
}
