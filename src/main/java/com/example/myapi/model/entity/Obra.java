package com.example.myapi.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "obras")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreObra;
    private String autor;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreObra() {
        return nombreObra;
    }
    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Object getCreatedAt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatedAt'");
    }
}
