package com.kanban.kanbanlab.entities.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String matchPassword;
}
