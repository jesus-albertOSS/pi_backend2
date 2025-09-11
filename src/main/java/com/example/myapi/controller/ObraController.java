package com.example.myapi.controller;

import com.example.myapi.model.entity.Obra;
import com.example.myapi.service.ObraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    private final ObraService obraService;

    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping
    public List<Obra> getAll() {
        return obraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> getById(@PathVariable Long id) {
        return obraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Obra create(@RequestBody Obra obra) {
        return obraService.save(obra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> update(@PathVariable Long id, @RequestBody Obra obra) {
        return ResponseEntity.ok(obraService.update(id, obra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        obraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
