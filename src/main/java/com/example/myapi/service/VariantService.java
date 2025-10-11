package com.example.myapi.service;

import com.example.myapi.model.entity.Variant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VariantService {
    List<Variant> findAll();
    Optional<Variant> findById(UUID id);
    Variant save(Variant variant);
    void deleteById(UUID id);
}
