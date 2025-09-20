package com.example.myapi.controller;

import com.example.myapi.model.entity.Address;
import com.example.myapi.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressRepository repo;

    public AddressController(AddressRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Address> getAll() {
        return (List<Address>) repo.findAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable UUID id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return repo.save(address);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable UUID id, @RequestBody Address address) {
        Address existing = repo.findById(id)
                               .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setId(existing.getId()); // aseguramos que se conserve el ID
        return repo.save(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}
