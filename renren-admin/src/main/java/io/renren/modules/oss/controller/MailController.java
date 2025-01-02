package io.renren.modules.oss.controller;

import io.renren.modules.oss.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {


    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public String sendEmail() {
        emailService.sendEmail("1083344129@qq.com", "Test Subject", "This is a test email.");
        return "Email Sent!";
    }
}
