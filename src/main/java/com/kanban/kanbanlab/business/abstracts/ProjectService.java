package com.kanban.kanbanlab.business.abstracts;

import com.kanban.kanbanlab.entities.concretes.Project;
import com.kanban.kanbanlab.entities.concretes.User;

import java.util.List;

public interface ProjectService {
    List<Project> getAll();
    Project getById(int id);
    List<Project> getAllByUserId(int id);
    List<User> getUsersByProjectId(int id);
    void add(Project project);
    void update(Project project);
    void delete(int id);
    void addUser(int projectId, int userId);
    void deleteUser(int projectId, int userId);
}
