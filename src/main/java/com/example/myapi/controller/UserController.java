package com.example.myapi.controller;

import com.example.myapi.model.entity.User;
import com.example.myapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        User existing = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setId(existing.getId());
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}
