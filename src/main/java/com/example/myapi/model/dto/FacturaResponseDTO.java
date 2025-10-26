package com.example.myapi.model.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaResponseDTO {
    private UUID id;
    private String nombreJuego;
    private BigDecimal precioTotal;
    private LocalDateTime fechaCompra;
    private UUID userId;
    private String userName;
    private UUID productId;
}
