package com.spring.emailsender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.emailsender.service.EmailService;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/schedule-email")
    public String scheduleEmail() {
        return "schedule-email";
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam("time") String time, @RequestParam("subject") String subject,
                            @RequestParam("message") String message, Model model) {
        // In a real application, use a scheduler to send the email at the specified time
        emailService.sendEmailToAll(subject, message);
        model.addAttribute("success", "Email sent successfully!");
        return "schedule-email";
    }
}