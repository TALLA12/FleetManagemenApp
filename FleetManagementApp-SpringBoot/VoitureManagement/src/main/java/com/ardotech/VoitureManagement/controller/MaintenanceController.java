package com.ardotech.VoitureManagement.controller;

import com.ardotech.VoitureManagement.Entity.Maintenance;
import com.ardotech.VoitureManagement.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Autowired
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<Maintenance> addMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance addedMaintenance = maintenanceService.addMaintenance(maintenance);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMaintenance);
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> allMaintenance() {
        List<Maintenance> maintenanceList = maintenanceService.allMaintenance();
        return ResponseEntity.ok(maintenanceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        return maintenance != null ? ResponseEntity.ok(maintenance) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody Maintenance updatedMaintenance) {
        Maintenance updatedMaintenanceResult = maintenanceService.updateMaintenance(id, updatedMaintenance);
        return updatedMaintenanceResult != null ? ResponseEntity.ok(updatedMaintenanceResult) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
