package com.example.myapi.repository;

import com.example.myapi.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // 🎯 Nuevo método: buscar productos por temática (sin distinguir mayúsculas/minúsculas)
    List<Product> findByTematicaIgnoreCase(String tematica);
}
