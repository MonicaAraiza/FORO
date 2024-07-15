package com.example.Foro_api.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Topic> topics;

    public String getUsername() {
    }

    // Getters y Setters
}
