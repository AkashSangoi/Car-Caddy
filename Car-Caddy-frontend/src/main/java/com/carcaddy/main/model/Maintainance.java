package com.carcaddy.main.model;

import java.util.Date;

public class Maintainance {
    private Integer maintenanceId;
    private Integer carId;
    private String description;
    private Integer maintenanceCost;
    private Date maintenance_date;
    private String maintenanceType;
    private String maintenanceStatus;

    public Maintainance() {
    }

    public Maintainance(Integer maintenanceId, Integer carId, String description, Integer maintenanceCost, Date maintenance_date, String maintenanceType, String maintenanceStatus) {
        this.maintenanceId = maintenanceId;
        this.carId = carId;
        this.description = description;
        this.maintenanceCost = maintenanceCost;
        this.maintenance_date = maintenance_date;
        this.maintenanceType = maintenanceType;
        this.maintenanceStatus = maintenanceStatus;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(Integer maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
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

    @Override
    public String toString() {
        return "Maintainance{" +
                "maintenanceId=" + maintenanceId +
                ", carId=" + carId +
                ", description='" + description + '\'' +
                ", maintenanceCost=" + maintenanceCost +
                ", maintenance_date=" + maintenance_date +
                ", maintenanceType='" + maintenanceType + '\'' +
                ", maintenanceStatus='" + maintenanceStatus + '\'' +
                '}';
    }
}

