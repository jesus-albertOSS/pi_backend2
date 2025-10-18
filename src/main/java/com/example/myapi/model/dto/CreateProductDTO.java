package com.example.myapi.model.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDTO {
    private String name;
    private String description;
    private String feature;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
}
