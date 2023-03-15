package com.kanban.kanbanlab.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name="cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    //@Column(name="proj_id")
    //private int projectId;
    @Column(name="state")
    private int state;
    @Column(name="card_name")
    private String name;
    @Column(name="card_desc")
    private String desc;
    @Column(name="create_date")
    private LocalDate createDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @OneToOne
    @JoinTable(
            name = "card_leaders",
            joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "leader_id", referencedColumnName ="id")
    )
    @JsonIgnoreProperties({"projects", "cards"})
    private User leader;
    @ManyToMany
    @JoinTable(
            name = "card_users",
            joinColumns = @JoinColumn(name="card_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    @JsonIgnoreProperties({"projects", "cards"})
    private List<User> cardUsers;

    @ManyToOne
    @JoinColumn(name = "proj_id")
    @JsonIgnoreProperties({"projectCards", "projectUsers", "leader"})
    private Project project;
}
