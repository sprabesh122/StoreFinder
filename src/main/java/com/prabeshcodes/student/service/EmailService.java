package com.prabeshcodes.student.service;

public interface EmailService {
    Boolean sendNewProductMail(String to, String subject, String text);
}