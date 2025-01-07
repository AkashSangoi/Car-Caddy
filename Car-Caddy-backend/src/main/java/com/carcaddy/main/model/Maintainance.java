package com.carcaddy.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Maintainance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;
    @NotNull(message = "Car Id is required")
    @Min(value = 1, message = "Car Id must be positive number")
    @JoinColumn
    private Integer carId;
    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;
    private Integer maintenanceCost;
    @CreationTimestamp
    private Date maintenance_date;
    @NotNull(message = "Maintenance type is required.")
    @Size(min = 3, max = 50, message = "Maintenance type must be between 3 and 50 characters.")
    @Column(length = 50)
    private String maintenanceType;
    @NotNull(message = "Maintenance status is required.")
    @Size(min = 3, max = 50, message = "Maintenance status must be between 3 and 50 characters.")
    @Column(length = 50)
    private String maintenanceStatus;

    @Override
    public String toString() {
        return "Maintainance{" +
                "maintenance_Id=" + maintenanceId +
                ", car_id=" + carId +
                ", description='" + description + '\'' +
                ", Maintenance_Cost=" + maintenanceCost +
                ", maintenance_date=" + maintenance_date +
                ", maintenanceType='" + maintenanceType + '\'' +
                ", maintenanceStatus='" + maintenanceStatus + '\'' +
                '}';
    }

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public void setMaintenance_Id(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMaintenance_date() {
        return maintenance_date;
    }

    public void setMaintenance_date(Date maintenance_date) {
        this.maintenance_date = maintenance_date;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }
}

