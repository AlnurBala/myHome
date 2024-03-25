package com.startup.myhome.service.impl;

import com.startup.myhome.dto.ContactForm;
import com.startup.myhome.service.ContactService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final JavaMailSender mailSender;

    @Value("${mail.to}")
    private String emailTo;
    @Value("${mail.from}")
    private String emailFrom;

    public void processContactForm(ContactForm contactForm) {
        log.info("The contact form is being sent: {}", contactForm);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(emailTo);
            helper.setSubject("My Home - Contact us");

            String htmlContent = "<html><body style=\"font-family: Arial, sans-serif;\">" +
                    "<div style=\"background-color: #f2f2f2; padding: 20px;\">" +
                    "<h2 style=\"color: #333;\">Contact Form Submission</h2>" +
                    "<p><strong style=\"color: #555;\">Name:</strong> " + contactForm.getName() + "</p>" +
                    "<p><strong style=\"color: #555;\">Phone Number:</strong> " + contactForm.getPhoneNumber() + "</p>" +
                    "<p><strong style=\"color: #555;\">Message:</strong> " + contactForm.getMessage() + "</p>" +
                    "</div></body></html>";

            helper.setText(htmlContent, true);
            helper.setFrom(emailFrom);

            mailSender.send(message);
            log.info("Contact form sent: {}", contactForm);
        } catch (MessagingException e) {
            log.error("Error while sending email: {}", e.getMessage(), e);
        }
    }

//    public void processContactForm(ContactForm contactForm) {
//        log.info("The contact form is being sent: {}", contactForm);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(emailTo);
//        message.setSubject("New Contact Form Submission");
//        message.setText("Name: " + contactForm.getName() +
//                "\nPhone Number: " + contactForm.getPhoneNumber() +
//                "\nMessage: " + contactForm.getMessage());
//
//        mailSender.send(message);
//        log.info("Contact form sent: {}", contactForm);
//    }
}
