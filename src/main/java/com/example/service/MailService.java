package com.example.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Mail;

public interface MailService{
    boolean sendMessage(Mail mail);
}
