package com.kanban.kanbanlab.business.abstracts;

import com.kanban.kanbanlab.entities.dto.LoginDto;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(SignUpDto signUpDto, String userRole);
    ResponseEntity<?> login(LoginDto loginDto);
}
