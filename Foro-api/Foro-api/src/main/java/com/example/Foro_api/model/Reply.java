package com.example.Foro_api.model;

import javax.persistence.*;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters y Setters
}
