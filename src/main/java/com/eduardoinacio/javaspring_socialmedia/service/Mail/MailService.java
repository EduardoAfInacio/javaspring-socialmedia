package com.eduardoinacio.javaspring_socialmedia.service.Mail;

import com.eduardoinacio.javaspring_socialmedia.service.RedisService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final RedisService redisService;

    public MailService(JavaMailSender mailSender, RedisService redisService) {
        this.mailSender = mailSender;
        this.redisService = redisService;
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
                redisService.pushEmail(mailDTO);
            }
        }
    }
}
