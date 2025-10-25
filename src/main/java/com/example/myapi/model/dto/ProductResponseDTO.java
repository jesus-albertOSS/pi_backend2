package com.example.myapi.model.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String feature;
    private BigDecimal price;
    private String imageUrl;
    private LocalDateTime createdAt;
}
