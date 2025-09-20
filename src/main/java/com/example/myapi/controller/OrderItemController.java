package com.example.myapi.controller;

import com.example.myapi.model.entity.OrderItem;
import com.example.myapi.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemRepository repo;

    public OrderItemController(OrderItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<OrderItem> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public OrderItem getById(@PathVariable UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    @PostMapping
    public OrderItem create(@RequestBody OrderItem orderItem) {
        return repo.save(orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem update(@PathVariable UUID id, @RequestBody OrderItem orderItem) {
        OrderItem existing = repo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not found"));
        orderItem.setId(existing.getId());
        return repo.save(orderItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}
