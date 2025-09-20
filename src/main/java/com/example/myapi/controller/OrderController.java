package com.example.myapi.controller;

import com.example.myapi.model.entity.Order;
import com.example.myapi.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Order> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return repo.save(order);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order order) {
        Order existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setId(existing.getId());
        return repo.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
