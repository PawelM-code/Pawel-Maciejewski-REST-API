package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    public void send(Mail mail){
        LOGGER.info("Starting email preparation...");
        try{
            javaMailSender.send(createMailMessage(mail));
            LOGGER.info("Email has been sent.");
        }catch (MailException e){
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getMailTo());
        if(mail.getToCc() != null){
            simpleMailMessage.setCc(mail.getToCc());
        }
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());
        return simpleMailMessage;
    }
}
