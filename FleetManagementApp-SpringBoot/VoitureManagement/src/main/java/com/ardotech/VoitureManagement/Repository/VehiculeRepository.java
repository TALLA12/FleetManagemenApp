package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {
}
