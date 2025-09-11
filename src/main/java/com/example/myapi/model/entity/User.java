package com.example.myapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    public enum TipoMembresiaEnum {
        SEMANAL,
        MENSUAL,
        ANUAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Campo de membresía (enum)
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TipoMembresiaEnum membresia;

    // Constructores
    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public User(String email, String name, TipoMembresiaEnum membresia) {
        this.email = email;
        this.name = name;
        this.membresia = membresia;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TipoMembresiaEnum getMembresia() {
        return membresia;
    }

    public void setMembresia(TipoMembresiaEnum membresia) {
        this.membresia = membresia;
    }
}
