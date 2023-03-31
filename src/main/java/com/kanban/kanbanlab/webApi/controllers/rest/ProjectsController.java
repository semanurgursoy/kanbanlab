package com.kanban.kanbanlab.webApi.controllers.rest;

import com.kanban.kanbanlab.business.abstracts.ProjectService;
import com.kanban.kanbanlab.entities.concretes.Project;
import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    private ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getall")
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/getallbyuserid")
    public List<Project> getAllByUserId(int id) {
        return projectService.getAllByUserId(id);
    }

    @GetMapping("/getallusersbyprojectid")
    public List<User> getUsersByProjectId(int id) {
        return projectService.getUsersByProjectId(id);
    }

    @PostMapping("/add")
    public void addProject(@RequestBody Project project) {
        projectService.add(project);
    }

    @PutMapping("/update")

    public void updateProject(@RequestBody Project project) {
        projectService.update(project);
    }

    @DeleteMapping("/delete")
    public void deleteProject(int id) {
        projectService.delete(id);
    }

    @PostMapping("/adduser")
    public void addUser(int projectId, int userId){ projectService.addUser(projectId, userId); }

    @DeleteMapping("/deleteuser")
    public void deleteUser(int projectId, int userId) {projectService.deleteUser(projectId, userId);}

}
