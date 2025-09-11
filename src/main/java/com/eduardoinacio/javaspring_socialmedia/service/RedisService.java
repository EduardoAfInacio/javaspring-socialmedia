package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.service.Mail.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, MailDTO> redisTemplate;

    public RedisService(RedisTemplate<String, MailDTO> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    private static final String EMAIL_QUEUE = "emailQueue";

    public void pushEmail(MailDTO mail){
        redisTemplate.opsForList().leftPush(EMAIL_QUEUE, mail);
    }

    public MailDTO popEmail(){
        return redisTemplate.opsForList().rightPop(EMAIL_QUEUE);
    }

    public List<MailDTO> getEmails(){
        return redisTemplate.opsForList().range(EMAIL_QUEUE, 0, -1);
    }
}
