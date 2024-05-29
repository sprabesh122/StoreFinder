package com.prabeshcodes.student.service.Impl;

import com.prabeshcodes.student.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class EmailServiceImpl implements EmailService {
 @Autowired
 private JavaMailSender emailSender;

 @Override
 public Boolean sendNewProductMail(String to, String subject, String text) {
 SimpleMailMessage message = new SimpleMailMessage();
 message.setTo(to);
 message.setSubject(subject);
 message.setText(text);
 emailSender.send(message);
 return true;
 }
}
