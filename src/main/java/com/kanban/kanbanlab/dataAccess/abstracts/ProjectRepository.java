package com.kanban.kanbanlab.dataAccess.abstracts;

import com.kanban.kanbanlab.entities.concretes.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
