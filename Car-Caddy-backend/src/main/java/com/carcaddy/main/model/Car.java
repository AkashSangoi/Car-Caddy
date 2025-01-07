package com.carcaddy.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Car {
    @Id
    private Integer carId;
    private String registrationNo;
    private String model;
    private String color;
    private Integer mileage;
    private Date lastMaintenance;
    @OneToMany(mappedBy ="carId")
    private List<Maintainance>maintainanceList;

    public Car() {
    }

    public Car(Integer carId, String registrationNo, String model, String color, Integer mileage, Date lastMaintenance, List<Maintainance> maintainanceList) {
        this.carId = carId;
        this.registrationNo = registrationNo;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.lastMaintenance = lastMaintenance;
        this.maintainanceList = maintainanceList;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Date getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public List<Maintainance> getMaintainanceList() {
        return maintainanceList;
    }

    public void setMaintainanceList(List<Maintainance> maintainanceList) {
        this.maintainanceList = maintainanceList;
    }
}
