package com.eduardoinacio.javaspring_socialmedia.service.Mail;

import com.eduardoinacio.javaspring_socialmedia.service.RedisService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(MailDTO mailDTO){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(mailDTO.getTo());
            mail.setSubject(mailDTO.getSubject());
            mail.setText(mailDTO.getMessage());
            mailSender.send(mail);
        }catch (Exception e){
            if(mailDTO.getRetryCount() < 3){
                mailDTO.setRetryCount(mailDTO.getRetryCount() + 1);
                RedisService.pushEmail(mailDTO);
            }
        }
    }
}
