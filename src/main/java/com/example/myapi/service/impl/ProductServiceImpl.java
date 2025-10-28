package com.example.myapi.service.impl;

import com.example.myapi.model.dto.CreateProductDTO;
import com.example.myapi.model.dto.ProductResponseDTO;
import com.example.myapi.model.entity.Product;
import com.example.myapi.repository.ProductRepository;
import com.example.myapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO findById(UUID id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public ProductResponseDTO create(CreateProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .feature(dto.getFeature())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .tematica(dto.getTematica()) // ✅ Agregado campo tematica
                .build();

        repository.save(product);
        return mapToDTO(product);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    // 🎮 Nuevo método para buscar productos por temática
    @Override
    public List<ProductResponseDTO> findByTematica(String tematica) {
        return repository.findByTematicaIgnoreCase(tematica)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ProductResponseDTO mapToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .feature(product.getFeature())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .tematica(product.getTematica()) // ✅ Campo tematica
                .createdAt(product.getCreatedAt())
                .build();
    }
}
