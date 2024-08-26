package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.Conducteur;
import com.ardotech.VoitureManagement.Repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConducteurService {

    @Autowired
    private ConducteurRepository conducteurRepository;

    public Conducteur addConducteur(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public List<Conducteur> allConducteurs() {
        return conducteurRepository.findAll();
    }

    public Conducteur getConducteurById(Long id) {
        return conducteurRepository.findById(id).orElse(null);
    }

    public Conducteur updateConducteur(Long id, Conducteur updatedConducteur) {
        Conducteur conducteur = conducteurRepository.findById(id).orElse(null);
        if (conducteur != null) {
            conducteur.setNom(updatedConducteur.getNom());
            conducteur.setPrenom(updatedConducteur.getPrenom());
            conducteur.setDateNaissance(updatedConducteur.getDateNaissance());
            conducteur.setNumeroTelephone(updatedConducteur.getNumeroTelephone());
            conducteur.setAdresse(updatedConducteur.getAdresse());
            conducteur.setEmail(updatedConducteur.getEmail());
            conducteur.setNumeroPermis(updatedConducteur.getNumeroPermis());
            conducteur.setDateDelivrancePermis(updatedConducteur.getDateDelivrancePermis());
            conducteur.setDateExpirationPermis(updatedConducteur.getDateExpirationPermis());
            conducteur.setTypePermis(updatedConducteur.getTypePermis());
            conducteur.setDateDebutEmploi(updatedConducteur.getDateDebutEmploi());
            conducteur.setStatutEmploi(updatedConducteur.getStatutEmploi());
            return conducteurRepository.save(conducteur);
        }
        return null;
    }

    public boolean deleteConducteur(Long id) {
        Conducteur conducteur = conducteurRepository.findById(id).orElse(null);
        if (conducteur != null) {
            conducteurRepository.delete(conducteur);
            return true;
        }
        return false;
    }
}
