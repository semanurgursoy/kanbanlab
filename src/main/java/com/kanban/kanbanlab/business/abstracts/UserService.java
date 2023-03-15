package com.kanban.kanbanlab.business.abstracts;

import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    List<List<Card>> getCardsByState(int userId);
    List<Card> getAllCardsByUser(int userId);
    User findUserByEmail(String email);
    User findUserByUserName(String name);
    User getById(int id);
    void add(User user);
    void forgotPassword(String email);
    void invite(String email);
    boolean existById(int id);
    boolean existByEmail(String email);

    //void login(User user);
}
