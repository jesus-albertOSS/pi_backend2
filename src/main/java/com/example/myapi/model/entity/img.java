package com.example.myapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "img")
public class img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String nameObra;

    @Column(nullable =true)
    private String autor;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    // Constructores, getters y setters
    public img() {
        this.createdAt = LocalDateTime.now();
    }

    public img(String nameObra, String autor) {
        this.nameObra = nameObra;
        this.autor = autor;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNameObra() {
        return nameObra;
    }

    public void setNameObra(String nameObra) {
        this.nameObra = nameObra;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
    