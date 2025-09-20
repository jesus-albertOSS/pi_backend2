package com.example.myapi.controller;

import com.example.myapi.model.entity.Customer;
import com.example.myapi.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable UUID id, @RequestBody Customer customer) {
        Customer existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setId(existing.getId());
        return repo.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}
