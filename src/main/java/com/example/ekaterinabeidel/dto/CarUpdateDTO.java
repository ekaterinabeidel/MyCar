package com.example.ekaterinabeidel.dto;

import lombok.Data;

@Data
public class CarUpdateDTO {
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private Long engineerId;
}
