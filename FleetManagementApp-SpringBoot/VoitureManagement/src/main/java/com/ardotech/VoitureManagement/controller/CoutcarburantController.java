package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.Coutcarburant;
import com.ardotech.VoitureManagement.Service.CoutcarburantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coutcarburant")
public class CoutcarburantController {

    @Autowired
    private final CoutcarburantService coutcarburantService;

    public CoutcarburantController(CoutcarburantService coutcarburantService) {
        this.coutcarburantService = coutcarburantService;
    }

    @PostMapping
    public ResponseEntity<Coutcarburant> addCoutcarburant(@RequestBody Coutcarburant coutcarburant) {
        Coutcarburant carburant = coutcarburantService.addCoutcarburant(coutcarburant);
        return ResponseEntity.status(HttpStatus.CREATED).body(carburant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coutcarburant> getCoutcarburantById(@PathVariable Long id) {
        Coutcarburant coutcarburant = coutcarburantService.getCoutcarburantById(id);
        return coutcarburant != null ? ResponseEntity.ok(coutcarburant) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Coutcarburant>> allCoutcarburant() {
        List<Coutcarburant> coutcarburantList = coutcarburantService.allCoutcarburant();
        return coutcarburantList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(coutcarburantList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coutcarburant> updateCoutcarburant(@PathVariable Long id, @RequestBody Coutcarburant coutcarburant) {
        Coutcarburant updatedCarburant = coutcarburantService.updateCoutcarburant(id, coutcarburant);
        return updatedCarburant != null ? ResponseEntity.ok(updatedCarburant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCoutcarburant(@PathVariable Long id) {
        boolean isDeleted = coutcarburantService.deleteCoutcarburant(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", isDeleted);
        return isDeleted ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
