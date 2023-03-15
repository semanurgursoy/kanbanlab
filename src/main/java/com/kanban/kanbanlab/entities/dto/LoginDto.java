package com.kanban.kanbanlab.entities.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;
}
