package com.carcaddy.main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maintenanceId;
    @NotNull(message = "Car Id is required")
            @Min(value = 1, message = "Car Id must be positive number")
            @JoinColumn
            @ManyToMany
    private String carId;  // Foreign key to Car
    private LocalDate maintenanceDate;
    private String maintenanceType;
    private String description;
    private BigDecimal maintenanceCost;
    private String status;

    // Getters and Setters
}

