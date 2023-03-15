package com.kanban.kanbanlab.business.abstracts;

import com.kanban.kanbanlab.entities.concretes.Role;

public interface RoleService {
    Role findByRole(String role);
}
