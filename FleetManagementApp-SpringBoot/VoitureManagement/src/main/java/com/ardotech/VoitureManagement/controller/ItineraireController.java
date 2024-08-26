package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.Itineraire;
import com.ardotech.VoitureManagement.Service.ItineraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/itineraire")
public class ItineraireController {

    @Autowired
    private final ItineraireService itineraireService;

    public ItineraireController(ItineraireService itineraireService) {
        this.itineraireService = itineraireService;
    }

    @PostMapping
    public ResponseEntity<Itineraire> addItineraire(@RequestBody Itineraire itineraire) {
        Itineraire addedItineraire = itineraireService.addItineraire(itineraire);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedItineraire);
    }

    @GetMapping
    public ResponseEntity<List<Itineraire>> allItineraire() {
        List<Itineraire> itineraireList = itineraireService.allItineraire();
        return ResponseEntity.ok(itineraireList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itineraire> getItineraireById(@PathVariable Long id) {
        Itineraire itineraire = itineraireService.getItineraireById(id);
        return itineraire != null ? ResponseEntity.ok(itineraire) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Itineraire> updateItineraire(@PathVariable Long id, @RequestBody Itineraire updatedItineraire) {
        Itineraire updatedItineraireResult = itineraireService.updateItineraire(id, updatedItineraire);
        return updatedItineraireResult != null ? ResponseEntity.ok(updatedItineraireResult) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteItineraire(@PathVariable Long id) {
        itineraireService.deleteItineraire(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
