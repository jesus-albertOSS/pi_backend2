package com.example.myapi.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDTO {
    private UUID id;
    private String name;
    private String slug;
    private String brand;
    private String description;
    private String feature;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private String imageUrl;
}
