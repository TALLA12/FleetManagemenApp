package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.Itineraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraireRepository extends JpaRepository<Itineraire,Long> {
}
