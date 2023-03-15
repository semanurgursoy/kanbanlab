package com.kanban.kanbanlab.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name="projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="project_name")
    private String name;
    @Column(name="project_desc")
    private String desc;
    @Column(name="create_date")
    private LocalDate createDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @OneToOne
    @JoinTable(
            name = "project_leaders",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "leader_id", referencedColumnName ="id")
    )
    @JsonIgnoreProperties({"projects", "cards"})
    private User leader;
    @ManyToMany
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    @JsonIgnoreProperties({"projects", "cards"})
    private List<User> projectUsers;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties({"project", "cardUsers", "leader"})
    private List<Card> projectCards;

}
