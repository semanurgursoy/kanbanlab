package com.kanban.kanbanlab.webApi.controllers.rest;

import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService) { this.userService = userService; }

    @GetMapping("/getall")
    public List<User> getAll() { return userService.getAll(); }

    @GetMapping("/finduserbyemail")
    public User findUserByEmail(String email) { return userService.findUserByEmail(email); }

    @GetMapping("/finduserbyname")
    public User findUserByName(String name) { return userService.findUserByUserName(name); }

    //@PostMapping("/add")
    //public void addUser(@RequestBody User user){ userService.add(user); }

    @PutMapping("/forgotpassword")
    public void forgotPassword(String email){ userService.forgotPassword(email); }

    @PostMapping("/invite")
    public void invite(String email) { userService.invite(email); }

}
