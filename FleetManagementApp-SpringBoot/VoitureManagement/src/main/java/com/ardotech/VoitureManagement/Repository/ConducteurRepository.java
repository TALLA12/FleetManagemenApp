package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.ardotech.VoitureManagement.Entity.Vehicule;
@Repository
public interface ConducteurRepository extends JpaRepository<Conducteur,Long> {
    List<Conducteur> findByVehiculeAssigneId(Vehicule vehicule);

}
