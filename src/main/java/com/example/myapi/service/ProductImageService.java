package com.example.myapi.service;

import com.example.myapi.model.entity.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    List<ProductImage> findAll();
    Optional<ProductImage> findById(Long id);
    ProductImage save(ProductImage productImage);
    void deleteById(Long id);
}
