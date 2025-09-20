package com.example.myapi.controller;

import com.example.myapi.model.entity.UserRole;
import com.example.myapi.repository.UserRoleRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleRepository repo;

    public UserRoleController(UserRoleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<UserRole> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public UserRole getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("UserRole not found"));
    }

    @PostMapping
    public UserRole create(@RequestBody UserRole userRole) {
        return repo.save(userRole);
    }

    @PutMapping("/{id}")
    public UserRole update(@PathVariable Long id, @RequestBody UserRole userRole) {
        UserRole existing = repo.findById(id).orElseThrow(() -> new RuntimeException("UserRole not found"));
        userRole.setId(existing.getId());
        return repo.save(userRole);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
