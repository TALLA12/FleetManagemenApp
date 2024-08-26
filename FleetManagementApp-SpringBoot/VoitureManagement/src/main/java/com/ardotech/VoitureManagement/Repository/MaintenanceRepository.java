package com.ardotech.VoitureManagement.Repository;

import com.ardotech.VoitureManagement.Entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
