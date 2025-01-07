package com.carcaddy.main.controller;

import com.carcaddy.main.exception.InvalidEntityException;
import com.carcaddy.main.model.Maintainance;
import com.carcaddy.main.services.MaintananceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintananceController {
    @Autowired
    MaintananceService maintananceService;

    @GetMapping("/data")
    public List<Maintainance> getAllMaintenance() {
        return maintananceService.getAllMaintenance();
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveMaintenanceRecord(@Valid @RequestBody Maintainance record) {
        try {
            // Check for existing record
            boolean isDuplicate = maintananceService.isDuplicateMaintenance(record);
            if (isDuplicate) {
                return new ResponseEntity<>("Duplicate maintenance request exists", HttpStatus.CONFLICT);
            }
            maintananceService.createMaintenance(record);
            return new ResponseEntity<>(record, HttpStatus.CREATED);
        } catch (InvalidEntityException ex) {
            return new ResponseEntity<>("Validation error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Handle Update Submission
    @PostMapping("/edit/{id}")
    public ResponseEntity<?> updateMaintenanceRecord(@PathVariable("id") Integer id, @RequestBody Maintainance record) {
        try {
            System.out.println(id);
            System.out.println(record);
            maintananceService.updateMaintenance(record);
            return new ResponseEntity<>("Records updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating record", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaintenanceRecord(@PathVariable("id") Integer id) {
        maintananceService.deleteMaintenance(id); // Call the service to delete the record
        return new ResponseEntity<>("Record deleted", HttpStatus.OK);
    }
}
