package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.BusinessRules;
import com.kanban.kanbanlab.business.abstracts.AuthService;
import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.entities.dto.LoginDto;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    @Autowired
    private final UserService userService;

    @Override
    public ResponseEntity<?> register(SignUpDto signUpDto, String userRole) {
        ResponseEntity checkUser = BusinessRules.Run(
                checkEmailExist(signUpDto.getEmail()),
                checkPassword(signUpDto.getPassword()),
                checkPasswordsMatches(signUpDto.getPassword(), signUpDto.getMatchPassword())
        );
        if(!checkUser.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkUser.getBody(), HttpStatus.BAD_REQUEST);
        userService.add(signUpDto);
        return new ResponseEntity<>(signUpDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        /*
        ResponseEntity checkUser = checkUserExist(loginDto.getEmail());
        if(!checkUser.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkUser.getBody(),HttpStatus.BAD_REQUEST);

        User user = userService.findUserByEmail(loginDto.getEmail());

        ResponseEntity checkPwd = checkUserPassword(loginDto.getPassword(), user.getPassword());
        if (!checkPwd.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkPwd.getBody(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user, HttpStatus.OK);
        */
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    private ResponseEntity<?> checkEmailExist(String email){
        if(userService.existByEmail(email))
            return new ResponseEntity<List<String>>(
                    new ArrayList<String>(Arrays.asList("email","email is already taken"))
                    , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> checkPassword(String pwd){
        int passwordLength=8;
        boolean special = false, digits = false,  upChars = false, lowChars = false;
        char ch;

        for(int i=0; i<pwd.length(); i++)
        {
            ch = pwd.charAt(i);
            if(Character.isUpperCase(ch))
                upChars = true;
            else if(Character.isLowerCase(ch))
                lowChars = true;
            else if(Character.isDigit(ch))
                digits = true;
            else
                special = true;
        }

        if(pwd.length()>passwordLength && upChars && lowChars && digits && special )
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<List<String>>(
                new ArrayList<String>(Arrays.asList("password","invalid password"))
                , HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> checkPasswordsMatches(String pwd1, String pwd2){
        if(pwd1.equals(pwd2))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<List<String>>(
                new ArrayList<String>(Arrays.asList("matchPassword","passwords doesn't match"))
                , HttpStatus.BAD_REQUEST);
    }
/*
    private ResponseEntity<String> checkUserExist(String email){
        if(userService.existByEmail(email))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("email", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> checkUserPassword(String loginPwd, String userPwd){
        if(!passwordEncoder.matches(loginPwd, userPwd))
            return new ResponseEntity<>("incorrect password", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(HttpStatus.OK);
    }
*/

}
