package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.abstracts.CardService;
import com.kanban.kanbanlab.business.abstracts.ProjectService;
import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.dataAccess.abstracts.CardRepository;
import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.Project;
import com.kanban.kanbanlab.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardManager implements CardService {
    private CardRepository cardRepository;
    private ProjectService projectService;
    private UserService userService;
    @Autowired
    public CardManager(CardRepository cardRepository, ProjectService projectService, UserService userService) {
        this.cardRepository = cardRepository;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public List<Card> getAllByProjectId(int id) {
        return cardRepository.findByProjectId(id);
    }

    @Override
    public List<User> getUsersByCardId(int id) { return cardRepository.findById(id).getCardUsers(); }

    @Override
    public Card getByCardId(int id){ return cardRepository.findById(id); }


    @Override
    public void add(Card card) {
        Project project = projectService.getById(card.getProject().getId());
        project.getProjectCards().add(card);
        projectService.add(project);
        card.setProject(project);
        cardRepository.save(card);
    }

    @Override
    public void update(Card card) {
        Card c = cardRepository.getReferenceById(card.getId());
        c.setName(card.getName());
        c.setDesc(card.getDesc());
        c.setCreateDate(card.getCreateDate());
        c.setEndDate(card.getEndDate());
        c.setCardUsers(card.getCardUsers());
        c.setState(card.getState());
        cardRepository.save(c);
    }

    @Override
    public void delete(int id) {
        cardRepository.deleteById(id);
    }

    @Override
    public void addUser(int cardId, int userId){
        Card card = cardRepository.findById(cardId);
        if(userService.existById(userId)){
            User user = userService.getById(userId);
            card.getCardUsers().add(user);
            cardRepository.save(card);
        }
        else{
            //send email
        }
    }

    @Override
    public void deleteUser(int cardId, int userId){
        Card card = cardRepository.findById(cardId);
        if(userService.existById(userId)){
            User user = userService.getById(userId);
            card.getCardUsers().remove(user);
            cardRepository.save(card);
        }
    }

}
