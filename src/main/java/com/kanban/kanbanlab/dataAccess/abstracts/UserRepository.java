package com.kanban.kanbanlab.dataAccess.abstracts;

import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByName(String name);
}
