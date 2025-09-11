package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.service.Mail.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {
    @Autowired
    private static RedisTemplate<String, MailDTO> redisTemplate;

    private static final String EMAIL_QUEUE = "emailQueue";

    public static void pushEmail(MailDTO mail){
        redisTemplate.opsForList().leftPush(EMAIL_QUEUE, mail);
    }

    public static void popEmail(){
        redisTemplate.opsForList().rightPop(EMAIL_QUEUE);
    }

    public static List<MailDTO> getEmails(){
        return redisTemplate.opsForList().range(EMAIL_QUEUE, 0, -1);
    }
}
