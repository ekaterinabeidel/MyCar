package com.example.ekaterinabeidel.util;

import com.example.ekaterinabeidel.dto.CarDTO;
import com.example.ekaterinabeidel.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarDTO mapToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setCityOfManufacture(car.getCityOfManufacture());
        carDTO.setEngineerDTO(EngineerMapper.mapToEngineerDTO(car.getEngineer()));
        return carDTO;
    }

    public static List<CarDTO> mapToListDTO (List<Car> cars){
        return cars.stream().map(CarMapper::mapToDTO).collect(Collectors.toList());
    }

    public static Car mapToEntity(CarDTO carDTO){
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setCityOfManufacture(carDTO.getCityOfManufacture());
        car.setEngineer(EngineerMapper.mapToEntity(carDTO.getEngineerDTO()));
        return car;
    }

}
