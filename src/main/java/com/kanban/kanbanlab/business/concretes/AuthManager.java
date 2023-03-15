package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.BusinessRules;
import com.kanban.kanbanlab.business.abstracts.AuthService;
import com.kanban.kanbanlab.business.abstracts.RoleService;
import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.entities.concretes.Role;
import com.kanban.kanbanlab.entities.concretes.User;
import com.kanban.kanbanlab.entities.dto.LoginDto;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthManager implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AuthManager(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<?> register(SignUpDto signUpDto, String userRole) {
        ResponseEntity checkUser = BusinessRules.Run(
                checkEmailExist(signUpDto.getEmail()),
                checkPassword(signUpDto.getPassword()),
                checkPasswordsMatches(signUpDto.getPassword(), signUpDto.getMatchPassword())
        );
        if(!checkUser.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkUser.getBody(), HttpStatus.BAD_REQUEST);

        String encodePwd = passwordEncoder.encode(signUpDto.getPassword());

        User user = new User();
        user.setName(signUpDto.getName());
        user.setSurname(signUpDto.getSurname());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(encodePwd);

        Role role = roleService.findByRole(userRole);
        user.setRole(role);

        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        ResponseEntity checkUser = checkUserExist(loginDto.getEmail());
        if(!checkUser.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkUser.getBody(),HttpStatus.BAD_REQUEST);

        User user = userService.findUserByEmail(loginDto.getEmail());

        ResponseEntity checkPwd = checkUserPassword(loginDto.getPassword(), user.getPassword());
        if (!checkPwd.getStatusCode().equals(HttpStatus.OK))
            return new ResponseEntity<>(checkPwd.getBody(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private ResponseEntity<String> checkEmailExist(String email){
        if(userService.existByEmail(email))
           return new ResponseEntity<>("email exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    private ResponseEntity<String> checkPassword(String pwd){
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
        return new ResponseEntity<>("invalid password", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> checkPasswordsMatches(String pwd1, String pwd2){
        if(pwd1.equals(pwd2))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("mahtching fail", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> checkUserExist(String email){
        if(userService.existByEmail(email))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> checkUserPassword(String loginPwd, String userPwd){
        if(!passwordEncoder.matches(loginPwd, userPwd))
            return new ResponseEntity<>("incorrect password", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
