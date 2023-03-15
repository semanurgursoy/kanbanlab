package com.kanban.kanbanlab.webApi.controllers;

import com.kanban.kanbanlab.business.abstracts.CardService;
import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cards")
@RestController
public class CardsController {
    private CardService cardService;
    @Autowired
    public CardsController(CardService cardService) {this.cardService = cardService; }

    @GetMapping("/getallbyprojectid")
    public List<Card> getAllByProjectId(int id) { return cardService.getAllByProjectId(id); }

    @GetMapping("/getusersbycardid")
    public List<User> getUsersByCardId(int id) { return cardService.getUsersByCardId(id); }

    @PostMapping("/add")
    public void addCard(@RequestBody Card card) { cardService.add(card); }

    @PutMapping("/update")
    public void updateCard(@RequestBody Card card) { cardService.update(card); }
    @DeleteMapping("/delete")
    public void deleteCard(int id) { cardService.delete(id); }

    @PostMapping("/adduser")
    public void addUser(int cardId, int userId){ cardService.addUser(cardId, userId); }

    @DeleteMapping("/deleteuser")
    public void deleteUser(int cardId, int userId) {cardService.deleteUser(cardId, userId);}

}
