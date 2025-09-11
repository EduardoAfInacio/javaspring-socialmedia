package com.eduardoinacio.javaspring_socialmedia.service.Mail;

import com.eduardoinacio.javaspring_socialmedia.service.RedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MailScheduler {
    private final MailService mailService;
    private final RedisService redisService;

    public MailScheduler(MailService mailService, RedisService redisService) {
        this.mailService = mailService;
        this.redisService = redisService;
    }

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void sendMail(){
        MailDTO mailDTO;
        while( (mailDTO = redisService.popEmail()) != null){
            mailService.sendMail(mailDTO);
        }
    }
}
