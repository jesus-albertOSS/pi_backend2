package com.example.myapi.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "membresias")
public class membresia {

    public enum TipoMembresiaEnum {
        SEMANAL,
        MENSUAL,
        ANUAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TipoMembresiaEnum tipoMembresia;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMembresiaEnum getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresiaEnum tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
}

    
   