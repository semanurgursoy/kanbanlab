package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.abstracts.ProjectService;
import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.dataAccess.abstracts.ProjectRepository;
import com.kanban.kanbanlab.entities.concretes.Project;
import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectManager implements ProjectService {
    private ProjectRepository projectRepository;
    private UserService userService;

    @Autowired
    public ProjectManager(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(int id) {
        return projectRepository.getById(id);
    }
    @Override
    public List<Project> getAllByUserId(int id){ return userService.getById(id).getProjects(); }

    @Override
    public List<User> getUsersByProjectId(int id){
        return projectRepository.findById(id).get().getProjectUsers();
    }

    @Override
    public void add(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void update(Project project) {
        Project p = projectRepository.getReferenceById(project.getId());
        p.setName(project.getName());
        p.setDesc(project.getDesc());
        p.setCreateDate(project.getCreateDate());
        p.setEndDate(project.getEndDate());
        p.setProjectUsers(project.getProjectUsers());
        projectRepository.save(p);
    }
    @Override
    public void delete(int id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void addUser(int projectId, int userId){
        Project project = projectRepository.getReferenceById(projectId);
        if(userService.existById(userId)){
           User user = userService.getById(userId);
           project.getProjectUsers().add(user);
           projectRepository.save(project);
        }
        else{
            //send email
        }
    }


    @Override
    public void deleteUser(int projectId, int userId){
        Project project = projectRepository.getReferenceById(projectId);
        if(userService.existById(projectId)){
            User user = userService.getById(userId);
            project.getProjectUsers().remove(user);
            projectRepository.save(project);
        }
    }


}
