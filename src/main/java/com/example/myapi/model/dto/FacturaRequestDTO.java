package com.example.myapi.model.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaRequestDTO {
    private UUID userId;
    private UUID productId;
}
