package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.Coutcarburant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoutcarburantRepository extends JpaRepository<Coutcarburant,Long> {
}
