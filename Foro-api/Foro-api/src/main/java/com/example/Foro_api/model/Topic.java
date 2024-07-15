package com.example.Foro_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "topic")
    private Set<Reply> replies;

    // Getters y Setters
}
