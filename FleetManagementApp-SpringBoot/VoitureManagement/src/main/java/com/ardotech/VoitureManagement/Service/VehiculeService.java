package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.Vehicule;
import com.ardotech.VoitureManagement.Repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import com.ardotech.VoitureManagement.Entity.Conducteur;

import com.ardotech.VoitureManagement.Repository.ConducteurRepository;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private ConducteurRepository conducteurRepository;

    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public List<Vehicule> allVehicule() {
        return vehiculeRepository.findAll();
    }

    public Vehicule updateVehicule(Long id, Vehicule updatedVehicule) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        if (vehicule != null) {
            vehicule.setMarque(updatedVehicule.getMarque());
            vehicule.setModele(updatedVehicule.getModele());
            vehicule.setPlaqueImmatriculation(updatedVehicule.getPlaqueImmatriculation());
            vehicule.setDateAchat(updatedVehicule.getDateAchat());
            vehicule.setType(updatedVehicule.getType());
            vehicule.setEtat(updatedVehicule.getEtat());
            return vehiculeRepository.save(vehicule); // Save and return the updated entity
        }
        return null;
    }

    public void deleteVehicule(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);

          if (vehicule !=null){

              vehiculeRepository.delete(vehicule);
          }


    }
}
