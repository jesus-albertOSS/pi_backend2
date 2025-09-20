package com.example.myapi.controller;

import com.example.myapi.model.entity.Variant;
import com.example.myapi.repository.VariantRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/variants")
public class VariantController {

    private final VariantRepository repo;

    public VariantController(VariantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Variant> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Variant getById(@PathVariable UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Variant not found"));
    }

    @PostMapping
    public Variant create(@RequestBody Variant variant) {
        return repo.save(variant);
    }

    @PutMapping("/{id}")
    public Variant update(@PathVariable UUID id, @RequestBody Variant variant) {
        Variant existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Variant not found"));
        variant.setId(existing.getId());
        return repo.save(variant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}
