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

    @Column(nullable = false, length = 100)
    private String name;

   
    @Column(length = 500)
    private String description;

  
    @Column(length = 500)
    private String feature;

   
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

 
    @Column(nullable = false)
    private Integer stock = 0;

  
    @Column(name = "image_url", length = 255)
    private String imageUrl;

   
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
