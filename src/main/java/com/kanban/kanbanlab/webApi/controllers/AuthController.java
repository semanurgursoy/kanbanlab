package com.kanban.kanbanlab.webApi.controllers;

import com.kanban.kanbanlab.business.abstracts.AuthService;
import com.kanban.kanbanlab.business.config.JwtUtils;
import com.kanban.kanbanlab.business.config.UserDetailService;
import com.kanban.kanbanlab.entities.concretes.User;
import com.kanban.kanbanlab.entities.dto.LoginDto;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtils jwtUtils;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto user){
        var loginUser = authService.login(user).getBody();
        if(loginUser.getClass() != User.class)
            return new ResponseEntity<>(loginUser.toString(), HttpStatus.BAD_REQUEST);
        //authenticationManager.authenticate(
        //        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        //);
        //final UserDetails userDetails = userDetailService.findUserByEmail(user.getEmail());
        //if(user != null)
        return new ResponseEntity<>(HttpStatus.OK);
        //return new ResponseEntity<>("Some error has occurred", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto user, String role){
        var registeredUser = authService.register(user, role).getBody();
        if(registeredUser.getClass() != User.class)
            return new ResponseEntity<>(registeredUser.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);

        /*var registeredUser = authService.register(user, role).getBody();
        if(registeredUser.getClass() != User.class)
            return new ResponseEntity<>(registeredUser.toString(), HttpStatus.BAD_REQUEST);
        final UserDetails userDetails = userDetailService.findUserByEmail(user.getEmail());
        if(userDetails != null)
            return new ResponseEntity<>(jwtUtils.generateToken(userDetails), HttpStatus.OK);
        return new ResponseEntity<>("Some error has occurred", HttpStatus.BAD_REQUEST);*/
    }




}




