package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.Conducteur;
import com.ardotech.VoitureManagement.Service.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conducteur")
public class ConducteurController {

    @Autowired
    private final ConducteurService conducteurService;

    public ConducteurController(ConducteurService conducteurService) {
        this.conducteurService = conducteurService;
    }

    @PostMapping
    public ResponseEntity<Conducteur> addConducteur(@RequestBody Conducteur conducteur){
        Conducteur savedConducteur = conducteurService.addConducteur(conducteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConducteur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conducteur> getConducteurById(@PathVariable Long id){
        Conducteur conducteur = conducteurService.getConducteurById(id);
        return conducteur != null ? ResponseEntity.ok(conducteur) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Conducteur>> allConducteur(){
        List<Conducteur> conducteurs = conducteurService.allConducteurs();
        return conducteurs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(conducteurs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conducteur> updateConducteur(@PathVariable Long id, @RequestBody Conducteur updatedConducteur){
        Conducteur updated = conducteurService.updateConducteur(id, updatedConducteur);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteConducteur(@PathVariable Long id){
        boolean isDeleted = conducteurService.deleteConducteur(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", isDeleted);
        return isDeleted ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
