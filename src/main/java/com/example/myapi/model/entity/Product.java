package com.example.myapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private String description; //string

    private String feature;

    private BigDecimal price;

    private int stock;

    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();
}
