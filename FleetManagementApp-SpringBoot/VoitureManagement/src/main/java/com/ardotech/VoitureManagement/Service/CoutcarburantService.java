package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Entity.Coutcarburant;
import com.ardotech.VoitureManagement.Repository.CoutcarburantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoutcarburantService {

    @Autowired
    private CoutcarburantRepository coutcarburantRepository;

    public Coutcarburant addCoutcarburant(Coutcarburant coutcarburant) {
        return coutcarburantRepository.save(coutcarburant);
    }

    public Coutcarburant getCoutcarburantById(Long id) {
        return coutcarburantRepository.findById(id).orElse(null);
    }

    public List<Coutcarburant> allCoutcarburant() {
        return coutcarburantRepository.findAll();
    }

    public Coutcarburant updateCoutcarburant(Long id, Coutcarburant updatedCoutcarburant) {
        Coutcarburant coutcarburant = coutcarburantRepository.findById(id).orElse(null);
        if (coutcarburant != null) {
            coutcarburant.setDate(updatedCoutcarburant.getDate());
            coutcarburant.setPrix(updatedCoutcarburant.getPrix());
            coutcarburant.setQuantite(updatedCoutcarburant.getQuantite());
            coutcarburant.setTotal(updatedCoutcarburant.getTotal());
            return coutcarburantRepository.save(coutcarburant);
        }
        return null;
    }

    public boolean deleteCoutcarburant(Long id) {
        Coutcarburant coutcarburant = coutcarburantRepository.findById(id).orElse(null);
        if (coutcarburant != null) {
            coutcarburantRepository.delete(coutcarburant);
            return true;
        }
        return false;
    }
}
