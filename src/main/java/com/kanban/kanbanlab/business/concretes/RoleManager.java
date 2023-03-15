package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.abstracts.RoleService;
import com.kanban.kanbanlab.dataAccess.abstracts.RoleRepository;
import com.kanban.kanbanlab.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleManager implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
