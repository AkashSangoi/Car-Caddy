package com.carcaddy.main.controller;

import com.carcaddy.main.exception.InvalidEntityException;
import com.carcaddy.main.model.Maintainance;
import com.carcaddy.main.services.MaintananceService;
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

    @GetMapping("/data/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable Integer id) {
        try {
            Maintainance maintainance = maintananceService.getById(id);
            return new ResponseEntity<>(maintainance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveMaintenanceRecord(@RequestBody Maintainance record) {
        try {
            System.out.println(record.toString());
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
            if(id==null){
                return new ResponseEntity<>("maintainence id is necessary",HttpStatus.NOT_FOUND);
            }
            record.setMaintenanceId(id);
            maintananceService.updateMaintenance(record);
            return new ResponseEntity<>("Records updated", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating record", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaintenanceRecord(@PathVariable("id") Integer id) {
        if (maintananceService.deleteMaintenance(id)) {
            return new ResponseEntity<>("Record deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Record Not found", HttpStatus.NOT_FOUND);
    }
}
