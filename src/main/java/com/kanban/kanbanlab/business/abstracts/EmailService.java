package com.kanban.kanbanlab.business.abstracts;


import com.kanban.kanbanlab.entities.concretes.EmailDetails;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details, MultipartFile file);
}
