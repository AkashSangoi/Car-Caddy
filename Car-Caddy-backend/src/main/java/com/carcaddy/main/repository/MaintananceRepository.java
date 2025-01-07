package com.carcaddy.main.repository;

import com.carcaddy.main.model.Maintainance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintananceRepository extends JpaRepository<Maintainance, Integer> {
    boolean existsByCarIdAndMaintenanceTypeAndMaintenanceStatus(Integer car_id,String maintenanceType, String maintenanceStatus);
}