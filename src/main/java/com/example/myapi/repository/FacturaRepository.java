package com.example.myapi.repository;

import com.example.myapi.model.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface FacturaRepository extends JpaRepository<Factura, UUID> {
    List<Factura> findByUserId(UUID userId);
}
