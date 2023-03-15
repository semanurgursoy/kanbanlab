package com.kanban.kanbanlab.dataAccess.abstracts;

import com.kanban.kanbanlab.entities.concretes.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByProjectId(int id);
    Card findById(int id);
}
