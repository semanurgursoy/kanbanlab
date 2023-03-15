package com.kanban.kanbanlab.webApi.controllers;

import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    /*@GetMapping("/home")
    public String homeView(Model model) {

        return "home";
    }*/
/*
    @PostMapping("/home")
    public String getCardsByState(@ModelAttribute("user") User user, Model model){
        List<List<Card>> total = userService.getCardsByState(user.getId());
        List<Card> todo = total.get(0);
        List<Card> doing = total.get(1);
        List<Card> done = total.get(2);
        //model.addAttribute("todo", todo);
        //model.addAttribute("doing", doing);
        //model.addAttribute("done", done);
        model.addAttribute("user", user);
        return "home";
    }*/

    @GetMapping("/home")
    public String homeView(@ModelAttribute("user") User user, Model model){
        //List<List<Card>> total = userService.getCardsByState(user.getId());
        //List<Card> todo = total.get(0);
        //List<Card> doing = total.get(1);
        //List<Card> done = total.get(2);
        //model.addAttribute("todo", todo);
        //model.addAttribute("doing", doing);
        //model.addAttribute("done", done);
        model.addAttribute("user", user);
        model.addAttribute("deneme", "deneme");
        return "home";
    }
}
