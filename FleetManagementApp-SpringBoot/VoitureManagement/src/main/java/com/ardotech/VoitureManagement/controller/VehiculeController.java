package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.Vehicule;
import com.ardotech.VoitureManagement.Service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicule")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @Autowired
    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule vehicule) {
        Vehicule addedVehicule = vehiculeService.addVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedVehicule);
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> allVehicule() {
        List<Vehicule> vehiculeList = vehiculeService.allVehicule();
        return ResponseEntity.ok(vehiculeList);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id){
        Vehicule vehicule= vehiculeService.getVehiculeById(id);
        return vehicule != null ? ResponseEntity.ok(vehicule) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody Vehicule updatedVehicule) {
        Vehicule vehicule = vehiculeService.updateVehicule(id, updatedVehicule);
        return vehicule != null ? ResponseEntity.ok(vehicule) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
