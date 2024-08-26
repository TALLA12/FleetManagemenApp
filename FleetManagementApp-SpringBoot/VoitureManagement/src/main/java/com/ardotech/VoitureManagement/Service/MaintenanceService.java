package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.Maintenance;
import com.ardotech.VoitureManagement.Repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public Maintenance addMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> allMaintenance() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElse(null);
    }

    public Maintenance updateMaintenance(Long id, Maintenance updatedMaintenance) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElse(null);
        if (maintenance != null) {
            maintenance.setDate(updatedMaintenance.getDate());
            maintenance.setCout(updatedMaintenance.getCout());
            maintenance.setDescription(updatedMaintenance.getDescription());
            maintenance.setVehiculeId(updatedMaintenance.getVehiculeId());
            return maintenanceRepository.save(maintenance); // Save and return the updated entity
        }
        return null;
    }

    public void deleteMaintenance(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElse(null);
        if (maintenance != null) {
            maintenanceRepository.delete(maintenance);
        }
    }
}
