package com.kanban.kanbanlab.webApi.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanban.kanbanlab.entities.concretes.EmailDetails;
import com.kanban.kanbanlab.business.abstracts.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class EmailController {

    private EmailService emailService;
    private static final Logger logger = Logger.getLogger(EmailController.class.getName());

    @Autowired
    public EmailController(EmailService emailService) {this.emailService = emailService;}

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details)
    {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    @PostMapping(value = "/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestParam("detail") String details, @RequestParam("file") MultipartFile file) throws IOException {
        EmailDetails email = new ObjectMapper().readValue(details, EmailDetails.class);
        String status = emailService.sendMailWithAttachment(email, file);

        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();

        logger.info("inputStream: " + inputStream);
        logger.info("originalName: " + originalName);
        logger.info("name: " + name);
        logger.info("contentType: " + contentType);
        logger.info("size: " + size);
        logger.info("details: " + email.getRecipient());

        return status;
    }

}
