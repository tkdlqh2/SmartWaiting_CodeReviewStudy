package com.example.smart_waiting.components;

import com.example.smart_waiting.SmartWaitingApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MailSenderAdapter {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(SmartWaitingApplication.class);

    public void sendMail(String mail, String subject, String text) {

        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
        };

        try {
            javaMailSender.send(msg);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

    }
}
