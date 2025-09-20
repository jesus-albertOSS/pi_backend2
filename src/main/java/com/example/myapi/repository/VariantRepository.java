package com.example.myapi.repository;

import com.example.myapi.model.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VariantRepository extends JpaRepository<Variant, UUID> {
}
