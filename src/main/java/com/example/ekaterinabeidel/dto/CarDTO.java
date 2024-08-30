package com.example.ekaterinabeidel.dto;

import com.example.ekaterinabeidel.entity.Engineer;
import lombok.Data;

@Data
public class CarDTO {
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private EngineerDTO engineerDTO;
}
