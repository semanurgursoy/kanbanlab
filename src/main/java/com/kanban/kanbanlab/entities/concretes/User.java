package com.kanban.kanbanlab.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="uname")
    private String name;
    @Column(name="usurname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="pwd")
    private String password;

    @ManyToMany(mappedBy = "projectUsers")
    @JsonIgnoreProperties("projectUsers")
    private List<Project> projects;
    @ManyToMany(mappedBy = "cardUsers")
    @JsonIgnoreProperties("cardUsers")
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
