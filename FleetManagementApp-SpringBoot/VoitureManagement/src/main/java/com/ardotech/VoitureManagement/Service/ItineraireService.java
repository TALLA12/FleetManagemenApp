package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.Itineraire;
import com.ardotech.VoitureManagement.Repository.ItineraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraireService {

    @Autowired
    private ItineraireRepository itineraireRepository;

    public Itineraire addItineraire(Itineraire itineraire) {
        return itineraireRepository.save(itineraire);
    }

    public List<Itineraire> allItineraire() {
        return itineraireRepository.findAll();
    }

    public Itineraire getItineraireById(Long id) {
        return itineraireRepository.findById(id).orElse(null);
    }

    public Itineraire updateItineraire(Long id, Itineraire updatedItineraire) {
        Itineraire itineraire = itineraireRepository.findById(id).orElse(null);
        if (itineraire != null) {
            itineraire.setOrigine(updatedItineraire.getOrigine());
            itineraire.setDestination(updatedItineraire.getDestination());
            itineraire.setDistance(updatedItineraire.getDistance());
            itineraire.setDuree(updatedItineraire.getDuree());
            itineraire.setDateDepart(updatedItineraire.getDateDepart());
            itineraire.setDateArrivee(updatedItineraire.getDateArrivee());

            itineraire.setConducteurId(updatedItineraire.getConducteurId());
            return itineraireRepository.save(itineraire); // Save and return the updated entity
        }
        return null;
    }

    public void deleteItineraire(Long id) {
        Itineraire itineraire = itineraireRepository.findById(id).orElse(null);
        if (itineraire != null) {
            itineraireRepository.delete(itineraire);
        }
    }
}
