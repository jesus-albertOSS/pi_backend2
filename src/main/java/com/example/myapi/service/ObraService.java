package com.example.myapi.service;

import com.example.myapi.model.entity.Obra;
import com.example.myapi.repository.ObraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraService {

    private final ObraRepository obraRepository;

    public ObraService(ObraRepository obraRepository) {
        this.obraRepository = obraRepository;
    }

    public List<Obra> findAll() {
        return obraRepository.findAll();
    }

    public Optional<Obra> findById(Long id) {
        return obraRepository.findById(id);
    }

    public Obra save(Obra obra) {
        return obraRepository.save(obra);
    }

    public Obra update(Long id, Obra obraDetails) {
        return obraRepository.findById(id).map(o -> {
            o.setAutor(obraDetails.getAutor());
            o.setNombreObra(obraDetails.getNombreObra());
            return obraRepository.save(o);
        }).orElseThrow(() -> new RuntimeException("Obra no encontrada con id " + id));
    }

    public void delete(Long id) {
        obraRepository.deleteById(id);
    }
}
