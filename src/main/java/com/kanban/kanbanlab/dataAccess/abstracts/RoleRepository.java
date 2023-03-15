package com.kanban.kanbanlab.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kanban.kanbanlab.entities.concretes.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
