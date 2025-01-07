package com.carcaddy.main.services;

import com.carcaddy.main.exception.InvalidEntityException;
import com.carcaddy.main.model.Maintainance;
import com.carcaddy.main.repository.MaintananceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintananceService {
    @Autowired
    MaintananceRepository maintananceRepository;

    public List<Maintainance> getAllMaintenance() {
        return maintananceRepository.findAll();
    }

    public boolean createMaintenance(Maintainance maintainance) {
        if (maintainance == null || maintainance.getCarId() == null) {
            throw new InvalidEntityException("Invalid maintenance record. Car ID is required.");
        }
       try {
           maintananceRepository.save(maintainance);
           return true;
       }
       catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
    }

    public Maintainance getById(Integer id) {
        Optional<Maintainance> maintainance = maintananceRepository.findById(id);
        return maintainance.orElseThrow(() -> new InvalidEntityException("Maintenance record with ID " + id + " not found."));
    }

    public void deleteMaintenance(Integer id) {
        if (!maintananceRepository.existsById(id)) {
            throw new InvalidEntityException("Cannot delete record. Maintenance ID " + id + " not found.");
        }
        maintananceRepository.deleteById(id); // Deletes the maintenance record by Id
    }

    public void updateMaintenance(Maintainance record) {
        if (!maintananceRepository.existsById(record.getMaintenanceId())) {
            throw new InvalidEntityException("Cannot update record. Maintenance ID " + record.getMaintenanceId() + " not found.");
        }
        maintananceRepository.save(record); // Save the updated record
    }

    // Get a record by ID
    public Maintainance getMaintenanceById(Integer id) {
        return maintananceRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException("Maintenance record not found for ID: " + id));
    }

    public boolean isDuplicateMaintenance(Maintainance record) {
        return maintananceRepository.existsByCarIdAndMaintenanceTypeAndMaintenanceStatus(
            record.getCarId(),
            record.getMaintenanceType(),
            record.getMaintenanceStatus()
        );
    }

}
