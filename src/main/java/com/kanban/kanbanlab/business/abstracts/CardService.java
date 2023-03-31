package com.kanban.kanbanlab.business.abstracts;

import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.User;

import java.util.List;

public interface CardService {
    List<Card> getAllByProjectId(int id);
    List<User> getUsersByCardId(int id);
    Card getByCardId(int id);
    void add(Card card);
    void update(Card card);
    void delete(int id);
    void addUser(int projectId, int userId);
    void deleteUser(int projectId, int userId);
}
