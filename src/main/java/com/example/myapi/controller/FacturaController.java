package com.example.myapi.controller;

import com.example.myapi.model.dto.FacturaRequestDTO;
import com.example.myapi.model.dto.FacturaResponseDTO;
import com.example.myapi.service.FacturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    // 🧾 Crear una nueva factura
    @PostMapping("/crear")
    public FacturaResponseDTO crearFactura(@RequestBody FacturaRequestDTO request) {
        return facturaService.crearFactura(request);
    }

    // 🔍 Obtener todas las facturas de un usuario específico
    @GetMapping("/usuario/{userId}")
    public List<FacturaResponseDTO> obtenerFacturasPorUsuario(@PathVariable UUID userId) {
        return facturaService.obtenerFacturasPorUsuario(userId);
    }

    // 📋 Obtener todas las facturas del sistema
    @GetMapping
    public List<FacturaResponseDTO> obtenerTodas() {
        return facturaService.obtenerTodas();
    }
}
