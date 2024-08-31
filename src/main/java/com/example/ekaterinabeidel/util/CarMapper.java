package com.example.ekaterinabeidel.util;

import com.example.ekaterinabeidel.dto.CarCreateDTO;
import com.example.ekaterinabeidel.dto.CarResponseDTO;
import com.example.ekaterinabeidel.dto.CarUpdateDTO;
import com.example.ekaterinabeidel.entity.Car;
import com.example.ekaterinabeidel.entity.Engineer;
import com.example.ekaterinabeidel.repository.EngineerRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarResponseDTO mapToCarResponseDTO(Car car) {
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(car.getId());
        carResponseDTO.setBrand(car.getBrand());
        carResponseDTO.setModel(car.getModel());
        carResponseDTO.setYear(car.getYear());
        carResponseDTO.setCityOfManufacture(car.getCityOfManufacture());
        carResponseDTO.setEngineerResponseDTO(EngineerMapper.mapToEngineerResponseDTO(car.getEngineer()));
        return  carResponseDTO;
    }

    public static List<CarResponseDTO> mapToListCarResponseDTO(List<Car> cars) {
        return cars.stream().map(CarMapper::mapToCarResponseDTO).collect(Collectors.toList());
    }

    public static Car mapCarCreateDTOToEntity(CarCreateDTO carCreateDTO) {
        Car car = new Car();
        car.setBrand(carCreateDTO.getBrand());
        car.setModel(carCreateDTO.getModel());
        car.setYear(carCreateDTO.getYear());
        car.setCityOfManufacture(carCreateDTO.getCityOfManufacture());
        return car;
    }

}
