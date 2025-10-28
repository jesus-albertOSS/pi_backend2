package com.example.myapi.service;

import com.example.myapi.model.dto.CreateProductDTO;
import com.example.myapi.model.dto.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductResponseDTO> findAll();

    ProductResponseDTO findById(UUID id);

    ProductResponseDTO create(CreateProductDTO dto);

    void delete(UUID id);

    // 🎮 Nuevo método: buscar productos por temática
    List<ProductResponseDTO> findByTematica(String tematica);
}
