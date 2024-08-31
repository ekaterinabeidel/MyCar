package com.example.ekaterinabeidel.dto;

import lombok.Data;

@Data
public class CarResponseDTO {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private EngineerResponseDTO engineerResponseDTO;
}

