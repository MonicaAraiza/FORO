package com.example.Foro_api.repository;

import com.example.Foro_api.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
