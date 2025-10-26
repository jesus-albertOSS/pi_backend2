package com.example.myapi.service;

import com.example.myapi.model.dto.FacturaRequestDTO;
import com.example.myapi.model.dto.FacturaResponseDTO;
import java.util.List;
import java.util.UUID;

public interface FacturaService {
    FacturaResponseDTO crearFactura(FacturaRequestDTO request);
    List<FacturaResponseDTO> obtenerFacturasPorUsuario(UUID userId);
    List<FacturaResponseDTO> obtenerTodas();
}
