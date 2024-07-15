package com.example.Foro_api.controller;


import com.example.Foro_api.model.Reply;
import com.example.Foro_api.model.Topic;
import com.example.Foro_api.model.User;
import com.example.Foro_api.payload.response.MessageResponse;
import com.example.Foro_api.repository.ReplyRepository;
import com.example.Foro_api.repository.TopicRepository;
import com.example.Foro_api.repository.UserRepository;
import com.example.Foro_api.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createReply(@RequestBody Reply reply, @RequestParam Long topicId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));

        reply.setUser(user);
        reply.setTopic(topic);

        replyRepository.save(reply);
        return ResponseEntity.ok(reply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReply(@PathVariable Long id, @RequestBody Reply replyDetails) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        reply.setContent(replyDetails.getContent());

        replyRepository.save(reply);
        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));

        replyRepository.delete(reply);
        return ResponseEntity.ok(new MessageResponse("Reply deleted successfully!"));
    }
}
