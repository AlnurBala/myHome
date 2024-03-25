package com.startup.myhome.controller;

import com.startup.myhome.dto.ContactForm;
import com.startup.myhome.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public String submitContactForm(@RequestBody @Valid ContactForm contactForm) {
        contactService.processContactForm(contactForm);
        return "Form submitted successfully";
    }
}
