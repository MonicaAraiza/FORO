package com.example.Foro_api.controller;

import com.example.Foro_api.model.Topic;
import com.example.Foro_api.model.User;
import com.example.Foro_api.request.JwtResponse.MessageResponse;
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
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody Topic topic) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userRepository.findById(userDetails.getId()).orElseThrow();
        topic.setUser(user);

        topicRepository.save(topic);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topic.setTitle(topicDetails.getTitle());
        topic.setContent(topicDetails.getContent());

        topicRepository.save(topic);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepository.delete(topic);
        return ResponseEntity.ok(new MessageResponse("Topic deleted successfully!"));
    }
}
