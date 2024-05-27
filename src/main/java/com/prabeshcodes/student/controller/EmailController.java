package com.prabeshcodes.student.controller;

import com.prabeshcodes.student.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.prabeshcodes.student.service.EmailService;
import com.prabeshcodes.student.model.Store;
import com.prabeshcodes.student.service.StoreService;

@RestController
@RequestMapping(value="/mail",method = { RequestMethod.GET, RequestMethod.POST })
//@CrossOrigin
public class EmailController {
 @Autowired
 private EmailService emailService;

 @Autowired
 private JwtUtil jwtUtil;

 @PostMapping("/sendmail")
 public Boolean sendmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
 return emailService.sendNewProductMail(to,subject,text);
 }
}
