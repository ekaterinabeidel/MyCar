package com.example.ekaterinabeidel.dto;

import com.example.ekaterinabeidel.Engineer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CarDTO {
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private Engineer engineer;
}
