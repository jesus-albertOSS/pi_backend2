package com.example.myapi.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateProductDTO {
    private String name;
    private String description;
    private String feature;
    private BigDecimal price;
    private String imageUrl;
}
