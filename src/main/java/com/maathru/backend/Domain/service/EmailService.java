package com.maathru.backend.Domain.service;

import com.maathru.backend.Domain.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.maathru.backend.External.utils.EmailUtils.getEmailMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private static final String NEW_USER_ACCOUNT_PASSWORD = "New User Account Password";

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendNewAccountEmail(String name, String email, String password) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_PASSWORD);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, password));
            sender.send(message);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("Unable to send email");
        }
    }
}
