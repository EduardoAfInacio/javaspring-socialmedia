package com.eduardoinacio.javaspring_socialmedia.service.Mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailDTO implements Serializable {
    private String to;
    private String subject;
    private String message;
    private int retryCount = 0;
}
