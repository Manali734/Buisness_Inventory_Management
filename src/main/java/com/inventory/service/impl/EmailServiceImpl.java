package com.inventory.service.impl;

import com.inventory.model.helper.Email;
import com.inventory.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public Email sendEmail(Email email) {
        //Used for plain text emails only
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject(email.getHeader());
        simpleMailMessage.setText(email.getMessage());
        simpleMailMessage.setFrom(from);
        javaMailSender.send(simpleMailMessage);
        return email;
    }

    @Override
    public Email sendHtmlEmail(Email email) throws MessagingException, IOException {
        //Supports HTML,Supports attachments,Supports CC/BCC,Supports inline images,Supports rich styling,Supports templates
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        ClassPathResource resource = new ClassPathResource("static/index.html");
        String message = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setTo(email.getTo());
        helper.setSubject(email.getHeader());
        helper.setText(message,true);
        javaMailSender.send(mimeMessage);
        return email;
    }

    @Override
    public Email sendEmailAttachment(Email email) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setTo(email.getTo());
        helper.setFrom(from);
        helper.setText(email.getMessage());
        helper.setSubject(email.getHeader());
        MultipartFile file = email.getFile();
        helper.addAttachment(file.getOriginalFilename(),file);
        javaMailSender.send(mimeMessage);
        return null;
    }

    @Override
    public Email sendEmailAttachments(Email email) throws MessagingException, IOException {
        return null;
    }
}
