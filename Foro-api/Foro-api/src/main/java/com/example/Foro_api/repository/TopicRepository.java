package com.example.Foro_api.repository;


import com.example.Foro_api.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
