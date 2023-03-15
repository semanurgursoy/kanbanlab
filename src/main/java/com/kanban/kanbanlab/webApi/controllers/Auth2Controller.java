package com.kanban.kanbanlab.webApi.controllers;

import com.kanban.kanbanlab.business.abstracts.AuthService;
import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.business.config.JwtUtils;
import com.kanban.kanbanlab.business.config.UserDetailService;
import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.User;
import com.kanban.kanbanlab.entities.dto.LoginDto;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class Auth2Controller {

    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtils jwtUtils;
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/home")
    public String login(@ModelAttribute("loginDtoForm") LoginDto user, Model m) {
        var loginUser = authService.login(user).getBody();
        if (loginUser.getClass() != User.class) {
            m.addAttribute("error", loginUser);
            return "login";
        }
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        final UserDetails userDetails = userDetailService.findUserByEmail(user.getEmail());
        if (userDetails == null) {
            m.addAttribute("access-error", "no access permission");
            return "login";
        }
        List<List<Card>> cards = userService.getCardsByState(((User) loginUser).getId());

        List<Card> allCards = userService.getAllCardsByUser(((User) loginUser).getId());

        m.addAttribute("user", loginUser);
        m.addAttribute("token", jwtUtils.generateToken(userDetails));
        m.addAttribute("cards", cards);
        m.addAttribute("allCards", allCards);
        //return "redirect:/home";
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        SignUpDto signUpDto = new SignUpDto();
        model.addAttribute("userRegistrationDto", signUpDto);

        return "register";
    }
    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("userRegistrationDto") SignUpDto signUpDto, Model model) {
        var registeredUser = authService.register(signUpDto, "user").getBody();
        if(registeredUser.getClass() != User.class){
            model.addAttribute("error", registeredUser);
            return "register";
        }
        final UserDetails userDetails = userDetailService.findUserByEmail(signUpDto.getEmail());
        if (userDetails == null) {
            model.addAttribute("access-error", "no access permission");
            return "register";
        }
        model.addAttribute("user", registeredUser);
        model.addAttribute(jwtUtils.generateToken(userDetails));
        return "home";
    }

}
