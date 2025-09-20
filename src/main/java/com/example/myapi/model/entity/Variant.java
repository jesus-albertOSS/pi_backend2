package com.example.myapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant {
    @Id
    @GeneratedValue
    private UUID id;

    private String color;
    private String colorName;
    private String storage;
    private Double price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
