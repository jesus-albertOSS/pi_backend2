package com.example.myapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String slug;
    private String brand;

    @Column(columnDefinition = "jsonb")
    private String description;

    @ElementCollection
    private List<String> features;

    @ElementCollection
    private List<String> images;
    
    private BigDecimal price;

    private LocalDateTime createdAt = LocalDateTime.now();
}
